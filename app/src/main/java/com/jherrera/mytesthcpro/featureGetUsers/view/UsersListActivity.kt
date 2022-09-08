package com.jherrera.mytesthcpro.featureGetUsers.view

import android.os.Bundle
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

    }
}