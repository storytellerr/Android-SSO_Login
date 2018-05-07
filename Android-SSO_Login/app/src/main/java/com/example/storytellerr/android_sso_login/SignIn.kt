package com.example.storytellerr.android_sso_login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignIn : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inte = getIntent()

        val name = getSharedPreferences("mypref", Context.MODE_PRIVATE).getString("loged_in","")
        if(name!=="") {
            val intent= Intent(this@SignIn, Home::class.java)
            if(inte.getType()!==null)
                intent.setType("text/plain")
            startActivity(intent)
            Log.d("signed-in already", "true")
        }
        else {
            setContentView(R.layout.activity_sign_in)
            Log.d("signed-in already", "false")
            registerButton.setOnClickListener {
                val intent = Intent(this@SignIn, SignUp::class.java)
                startActivity(intent)
            }

            loginButton.setOnClickListener {
                if(email.text.isEmpty()||password.text.isEmpty())
                    Toast.makeText(this@SignIn,"please fill all entities", Toast.LENGTH_LONG).show()
                else {
                    auth = FirebaseAuth.getInstance()
                    auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).
                            addOnCompleteListener { task: Task<AuthResult> ->
                                if (task.isSuccessful) {

                                    val intentToMain = Intent(this@SignIn, Home::class.java)
                                    val editor = getSharedPreferences("mypref", Context.MODE_PRIVATE).edit()
                                    editor.putString("loged_in", "true")
                                    editor.apply()
                                    if (inte.getType() !== null)
                                        intentToMain.setType("text/plain")
                                    startActivity(intentToMain)
                                    finish()
                                }
                                else{
                                    Toast.makeText(this@SignIn,"Wrong usename password", Toast.LENGTH_LONG).show()

                                }
                            }
                }
            }
        }
    }
}
