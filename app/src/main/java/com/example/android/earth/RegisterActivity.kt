package com.example.android.earth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btn_Register)
        val etRegEmail = findViewById<EditText>(R.id.ET_RegisterEmail)
        val etRegPassword = findViewById<EditText>(R.id.ET_RegisterPassword)
        val tvLoginAcc = findViewById<TextView>(R.id.TV_LoginAcc)

        tvLoginAcc.setOnClickListener(){
            onBackPressed()
        }

        btnRegister.setOnClickListener(){
            when{
                TextUtils.isEmpty(etRegEmail.text.toString().trim(){it <= ' '})->{
                    Toast.makeText(this,"Please enter Email",Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(etRegPassword.text.toString().trim(){it <= ' '})->{
                    Toast.makeText(this,"Please enter Password",Toast.LENGTH_SHORT).show()

            }
            else -> {
                val email: String = etRegEmail.text.toString().trim(){it<=' '}
                val password: String = etRegPassword.text.toString().trim(){it<=' '}

                //create an instance and register user
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                    //if registration is successfully done
                    if (task.isSuccessful) {
                        //firebase registered user
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        Toast.makeText(this,"You are Registered successfully.",Toast.LENGTH_SHORT).show()

                        val intent = Intent(this,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()



                    }
                    else{
                        //if reg is not successful
                        Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                    }
                }


            }

        }





    }
    }
}
