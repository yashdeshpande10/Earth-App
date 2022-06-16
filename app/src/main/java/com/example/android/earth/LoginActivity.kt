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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btn_Login)
        val etLoginEmail = findViewById<EditText>(R.id.ET_LoginEmail)
        val etLoginPassword = findViewById<EditText>(R.id.ET_LoginPassword)
        val tvRegAcc = findViewById<TextView>(R.id.TV_RegisterAcc)


        tvRegAcc.setOnClickListener() {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener() {
            when {
                TextUtils.isEmpty(etLoginEmail.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(etLoginPassword.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show()

                }
                else -> {
                    val email: String = etLoginEmail.text.toString().trim() { it <= ' ' }
                    val password: String = etLoginPassword.text.toString().trim() { it <= ' ' }

                    //login using firebase auth
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->


                        if (task.isSuccessful) {



                            Toast.makeText(this, "You are Logged in successfully.", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()


                        } else {
                            //if login is not successful
                            Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }


                }

            }
        }
    }

}
