package com.example.android.earth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.trimmedLength

class CreatePostActivity : AppCompatActivity() {
    private lateinit var postDao: PostDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        postDao = PostDAO()

        val btnPost = findViewById<Button>(R.id.btn_post)
        val ET_product_category = findViewById<EditText>(R.id.ET_product_category)
        val ET_product_name = findViewById<EditText>(R.id.ET_product_name)
        val ET_product_price= findViewById<EditText>(R.id.ET_product_price)
        val ET_product_rate= findViewById<EditText>(R.id.ET_product_rate)
        val ET_user_name= findViewById<EditText>(R.id.ET_user_name)
        val ET_user_mobile = findViewById<EditText>(R.id.ET_user_mobile)

        btnPost.setOnClickListener {
            val ipPC = ET_product_category.text.toString()
            val ipPN = ET_product_name.text.toString()
            val ipPP = ET_product_price.text.toString()
            val ipPR = ET_product_rate.text.toString()
            val ipUN = ET_user_name.text.toString()
            val ipUM = ET_user_mobile.text.toString()

            if (ipPC.isNotEmpty() and ipPN.isNotEmpty() and ipPP.isNotEmpty() and ipPR.isNotEmpty()
                and ipUN.isNotEmpty() and ipUM.isNotEmpty() and ipPC.equals("Laptop") or ipPC.equals("Smartphone")){
                postDao.addPost(ipPC,ipPN,ipPP,ipPR,ipUN,ipUM)
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Product posted successfully",Toast.LENGTH_LONG).show()
            }
            else if (ipPC.isEmpty() or ipPN.isEmpty() or ipPP.isEmpty() or ipPR.isEmpty() or
                    ipUN.isEmpty() or ipUM.isEmpty()){
                Toast.makeText(this,"All inputs should be filled",Toast.LENGTH_LONG).show()
            }
            else if (!ipPC.equals("Laptop") or !ipPC.equals("Smartphone")){
                Toast.makeText(this,"Only 'Smartphone' and 'Laptops' Category valid",Toast.LENGTH_LONG).show()

            }




         }
    }
}