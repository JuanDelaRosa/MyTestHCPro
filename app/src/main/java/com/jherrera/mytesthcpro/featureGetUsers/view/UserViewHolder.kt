package com.jherrera.mytesthcpro.featureGetUsers.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jherrera.domain.entities.User
import com.jherrera.mytesthcpro.databinding.UserItemBinding
import com.jherrera.mytesthcpro.featureGetUsers.viewmodel.*


class UserViewHolder(private val binding: UserItemBinding, private val viewModel: UsersListViewModel): RecyclerView.ViewHolder(binding.root) {

    private val _posts = MutableLiveData<Int?>()
    private val posts: LiveData<Int?> = _posts

    fun bind(user: User) {
        binding.company.text = user.company.name
        binding.catchPhrase.text = user.company.catchPhrase
        binding.name.text = user.name
        binding.website.text = user.website
        binding.address.text = user.getAddress()
        viewModel.getPosts(user.id, _posts)

        posts.observe(itemView.context as LifecycleOwner){
            binding.posts.text = it.toString()
        }
        binding.phone.setOnClickListener {
            viewModel.triggerEvent(OpenPhone(user.getPhoneNumber()))
        }

        binding.email.setOnClickListener {
            viewModel.triggerEvent(OpenEmail(user.email))
        }

        binding.website.setOnClickListener {
            viewModel.triggerEvent(OpenWebsite(user.website))
        }

        binding.map.setOnClickListener {
            viewModel.triggerEvent(OpenMap(user.address.geo.lat, user.address.geo.lng, user.address.street))
        }
    }
}