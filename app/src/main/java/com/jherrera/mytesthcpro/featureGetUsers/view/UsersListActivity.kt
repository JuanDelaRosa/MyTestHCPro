package com.jherrera.mytesthcpro.featureGetUsers.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jherrera.mytesthcpro.app.HousecallProApp
import com.jherrera.mytesthcpro.databinding.ActivityUsersListBinding
import com.jherrera.mytesthcpro.featureGetUsers.viewmodel.UsersListViewModel

class UsersListActivity : AppCompatActivity() {
    private val binding: ActivityUsersListBinding by lazy {
        ActivityUsersListBinding.inflate(layoutInflater)
    }
    private val viewModel: UsersListViewModel by lazy {
        UsersListViewModel.UsersListViewModelFactory((application as HousecallProApp).getUsers).create(UsersListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        viewModel.getUsers()
        viewModel.list.observe(this) { users ->
            var x = users
        }
        viewModel.error.observe(this) { error ->
            var x = error
        }
        viewModel.dataLoading.observe(this) { loading ->
            binding.pbLoading.visibility = if(loading) View.VISIBLE else View.GONE
        }
    }
}