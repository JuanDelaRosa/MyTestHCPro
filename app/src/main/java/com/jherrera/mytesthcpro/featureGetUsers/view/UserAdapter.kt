package com.jherrera.mytesthcpro.featureGetUsers.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jherrera.domain.entities.User
import com.jherrera.mytesthcpro.databinding.UserItemBinding

class UserAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val users: MutableList<User> = mutableListOf()

    fun setData(list: List<User>){
        users.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        users.getOrNull(position)?.let { user ->
            (holder as UserViewHolder).bind(user)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }
}