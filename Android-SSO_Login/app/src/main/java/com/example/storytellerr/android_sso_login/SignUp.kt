package com.example.storytellerr.android_sso_login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.sign_up.*

class SignUp : AppCompatActivity(){

    private lateinit var auth:FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        val inte=getIntent()

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        dbRef = database.reference



        registerActionButton.setOnClickListener(){
            if(emailRegister.text.isEmpty()||passwordRegister.text.isEmpty()||displayName.text.isEmpty()||Age.text.isEmpty())
                Toast.makeText(this@SignUp,"please fill all entities",Toast.LENGTH_LONG).show()
            else{
                    auth.createUserWithEmailAndPassword(emailRegister.text.toString(), passwordRegister.text.toString()).
                            addOnCompleteListener { task: Task<AuthResult> ->
                                if (task.isSuccessful) {
                                    val userId = auth.currentUser?.uid
                                    val registerRef = dbRef.child("user").child(userId)
                                    val user = User(displayName.text.toString(), Age.text.toString(), emailRegister.text.toString())
                                    registerRef.setValue(user).addOnSuccessListener() {
                                        val intent = Intent(this@SignUp, SignIn::class.java)
                                        if (intent.getType() !== null && intent.getType()?.toString() == "text/plain")
                                            intent.setType("text/plain")
                                        startActivity(intent)
                                        finish()
                                    }
                                }

                            }
            }
        }
        goback.setOnClickListener()
        {
            finish()
        }
    }


}
