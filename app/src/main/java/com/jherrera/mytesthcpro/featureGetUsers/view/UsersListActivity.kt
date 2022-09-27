package com.jherrera.mytesthcpro.featureGetUsers.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jherrera.mytesthcpro.app.HousecallProApp
import com.jherrera.mytesthcpro.app.LayoutUtils
import com.jherrera.mytesthcpro.databinding.ActivityUsersListBinding
import com.jherrera.mytesthcpro.featureGetUsers.interactor.GetUsersInteractor
import com.jherrera.mytesthcpro.featureGetUsers.viewmodel.*

class UsersListActivity : AppCompatActivity() {
    private val binding: ActivityUsersListBinding by lazy {
        ActivityUsersListBinding.inflate(layoutInflater)
    }
    private val viewModel: UsersListViewModel by lazy {
        UsersListViewModel.UsersListViewModelFactory(GetUsersInteractor((application as HousecallProApp).repository)).create(UsersListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.list.adapter = UserAdapter(viewModel)

        viewModel.getUsers()
        viewModel.list.observe(this) { users ->
            users?.let { it ->
                (binding.list.adapter as UserAdapter).setData(it)
            }
        }
        viewModel.error.observe(this) { error ->
            LayoutUtils.showSnack(binding.root, error)
        }
        viewModel.dataLoading.observe(this) { loading ->
            binding.pbLoading.visibility = if(loading) View.VISIBLE else View.GONE
        }

        lifecycleScope.launchWhenStarted {
            viewModel.events.collect{ event ->
                when(event){
                    is OpenPhone -> {
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:${event.phoneNumber}")
                        startActivity(intent)
                    }
                    is OpenEmail -> {
                        val intent = Intent(Intent.ACTION_SENDTO)
                        intent.data = Uri.parse("mailto:${event.email}")
                        intent.putExtra(Intent.EXTRA_SUBJECT, "I need a service")
                        intent.putExtra(Intent.EXTRA_TEXT, "Describe your problem")
                        startActivity(Intent.createChooser(intent, "Send Email"))
                    }
                    is OpenMap -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=loc:${event.lat},${event.lng} (${event.street})"))
                        startActivity(intent)
                    }
                    is OpenWebsite -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://${event.website}"))
                        startActivity(intent)
                    }
                }
            }
        }
    }
}