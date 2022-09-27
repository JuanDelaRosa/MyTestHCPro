package com.jherrera.mytesthcpro.featureGetUsers.view

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.jherrera.domain.entities.User
import com.jherrera.mytesthcpro.databinding.UserItemBinding


class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.company.text = user.company.name
        binding.catchPhrase.text = user.company.catchPhrase
        binding.name.text = user.name
        binding.website.text = user.website
        binding.address.text = user.getAddress()
        binding.posts.text = user.postCount.toString()

        binding.phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${user.getPhoneNumber()}")
            it.context.startActivity(intent)
        }

        binding.email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${user.email}")
            intent.putExtra(Intent.EXTRA_SUBJECT, "I need a service")
            intent.putExtra(Intent.EXTRA_TEXT, "Describe your problem")
            it.context.startActivity(Intent.createChooser(intent, "Send Email"))
        }

        binding.website.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://${user.website}"))
            it.context.startActivity(intent)
        }

        binding.map.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=loc:${user.address.geo.lat},${user.address.geo.lng} (${user.address.street})"))
            it.context.startActivity(intent)
        }
    }
}