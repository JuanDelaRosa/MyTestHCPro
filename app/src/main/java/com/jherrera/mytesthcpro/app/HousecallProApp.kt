package com.jherrera.mytesthcpro.app

import android.app.Application
import android.content.Context
import com.jherrera.domain.repositories.HousecallProRepository
import com.jherrera.domain.use_cases.GetPostsUC
import com.jherrera.domain.use_cases.GetUsersUC

class HousecallProApp: Application() {

    var context: Context = this

    private val repository: HousecallProRepository
        get() = ServiceLocator.provideRepository(context)

    val getUsers: GetUsersUC
        get() = GetUsersUC(repository)

    val getPosts: GetPostsUC
        get() = GetPostsUC(repository)
}


