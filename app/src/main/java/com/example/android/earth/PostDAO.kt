package com.example.android.earth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PostDAO {
    val db = FirebaseFirestore.getInstance()
    val postCollections = db.collection("posts")
    val auth = FirebaseAuth.getInstance()

    fun addPost(product_category: String,product_name: String, product_specs: String,
                product_condition: String, user_name: String, user_mobile: String){


        GlobalScope.launch {
            val post = Post(product_category,product_name,product_specs,product_condition,user_name,user_mobile)
            postCollections.document().set(post)
        }
    }
}