package com.example.android.earth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.Query

class HomeFragment : Fragment() {

    private lateinit var adapter: PostAdapter
    private lateinit var postDAO: PostDAO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.home_fragment, container, false)

        val btnFab = view.findViewById<FloatingActionButton>(R.id.fab)
        btnFab.setOnClickListener {
            val intent = Intent(activity,CreatePostActivity::class.java)
            startActivity(intent)
        }



        setUpRecyclerView(view)





        return view

    }

    private fun setUpRecyclerView(view: View) {



        postDAO = PostDAO()
        val postCollections = postDAO.postCollections
        val query = postCollections.orderBy("product_specs", Query.Direction.ASCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()

        adapter = PostAdapter(recyclerViewOptions)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

}



















