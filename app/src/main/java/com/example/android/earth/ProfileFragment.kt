package com.example.android.earth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private lateinit var tvEmail: TextView
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        //display email- fetch from db
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val email = user?.email
        tvEmail = view.findViewById(R.id.tvUserEmail)
        tvEmail.text = email

        //logout button
        val btnLogout = view.findViewById<Button>(R.id.btn_Logout)
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
        }


        return view


}}


