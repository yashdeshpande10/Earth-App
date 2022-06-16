package com.example.android.earth

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class PostAdapter(options: FirestoreRecyclerOptions<Post>) : FirestoreRecyclerAdapter<Post,PostAdapter.PostViewHolder>(
    options
){

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val sellerName: TextView = itemView.findViewById(R.id.tv_item_sellerName)
        val sellerNumber: TextView = itemView.findViewById(R.id.tv_item_sellerNumber)
        val prodCategory: TextView = itemView.findViewById(R.id.tv_item_category)
        val prodName: TextView = itemView.findViewById(R.id.tv_item_name)
        val prodModel: TextView = itemView.findViewById(R.id.tv_item_model)
        val prodCondition: TextView = itemView.findViewById(R.id.tv_item_condition)
        val btnEnquire: Button = itemView.findViewById(R.id.btn_enquire)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.sellerName.text = model.user_name
        holder.sellerNumber.text = model.user_mobile
        holder.prodCategory.text = model.product_category
        holder.prodName.text = model.product_name
        holder.prodModel.text = model.product_specs
        holder.prodCondition.text = model.product_condition

        holder.btnEnquire.setOnClickListener {
            val mobileNumber = model.user_mobile
            val callIntent: Intent = Uri.parse("tel:"+mobileNumber).let { mobileNumber ->
                Intent(Intent.ACTION_DIAL, mobileNumber)
            }
            startActivity(holder.sellerNumber.context,callIntent,null)
        }
    }
}