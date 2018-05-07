package com.example.storytellerr.android_sso_login


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_permission.*


class Home : AppCompatActivity(),View.OnClickListener {


    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inte = getIntent()

        val name = getSharedPreferences("mypref", Context.MODE_PRIVATE).getString("loged_in","")
        if(name!=="")
        {
            if(inte.getType()!==null) {
                setContentView(R.layout.activity_permission)
               var appName= inte.getStringExtra("Name")
                textView.setText(appName+getString(R.string.textdata))
                allow.setOnClickListener(this)
                cancel.setOnClickListener(this)
            }
            else {
                setContentView(R.layout.activity_home)
                auth = FirebaseAuth.getInstance()
                database = FirebaseDatabase.getInstance()
                auth.currentUser?.uid?.let { loadData(it)  }

                singoutButton.setOnClickListener() {
                    auth.signOut()
                    getSharedPreferences("mypref", Context.MODE_PRIVATE).edit().clear().commit()
                    val intent = Intent(this@Home, SignIn::class.java)
                    startActivity(intent)
                    finish()
                }

            }
        }
        else
        {
            val intent = Intent(this@Home, SignIn::class.java)
            if(inte.getType()!==null)
                intent.setType("auth")
            startActivity(intent)
            finish()
        }


    }
    private fun loadData(userId: String){
        val dataListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    var user: User? = dataSnapshot.getValue(User::class.java)
                    Log.d("hello",user.toString())
                    nameTextView.text = user?.displayName
                    statusTextView.text = user?.age
                    emailTextView.text= user?.email
                    if(user?.displayName!==null) {
                        val editor = getSharedPreferences("mypref", Context.MODE_PRIVATE).edit()
                        editor.putString("name", user.displayName)
                        editor.putString("age", user.age)
                        editor.putString("email", user.email)
                        editor.putString("loged_in","true")
                        editor.apply()
                    }
                }

            }

            override fun onCancelled(p0: DatabaseError?) {
                //
            }
        }
        database.reference.child("user")
                .child(userId).addListenerForSingleValueEvent(dataListener)

    }

    override fun onClick(p0: View?) {
        if(p0?.id==R.id.allow) {
            val intent = Intent()
            val sF = getSharedPreferences("mypref", Context.MODE_PRIVATE)
            intent.putExtra("name", sF.getString("name", "error"))
            intent.putExtra("age", sF.getString("age", "error"))
            intent.putExtra("email", sF.getString("email", "error"))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        if(p0?.getId()==R.id.cancel)
        {
            finish()
        }
    }



}
