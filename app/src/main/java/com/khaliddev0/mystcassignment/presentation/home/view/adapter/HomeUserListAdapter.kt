package com.khaliddev0.mystcassignment.presentation.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khaliddev0.mystcassignment.R
import com.khaliddev0.mystcassignment.domain.user.model.User

class HomeUserListAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<HomeUserListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userNameView: TextView = view.findViewById(R.id.user_name)
        private val userEmailView: TextView = view.findViewById(R.id.user_email)
        private val userImageView: ImageView = view.findViewById(R.id.user_image)

        fun bind(userName: String, userEmail: String, userImageUrl: String) {
            userNameView.text = userName
            userEmailView.text = userEmail
            Glide.with(itemView)
                .load(userImageUrl)
                .into(userImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_user_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            userName = userList[position].fullName,
            userEmail = userList[position].email,
            userImageUrl = userList[position].imageUrl,
        )
    }

    override fun getItemCount() = userList.size
}