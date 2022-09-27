package com.jherrera.mytesthcpro.featureGetUsers.interactor

import com.jherrera.domain.repositories.HousecallProRepository
import com.jherrera.domain.use_cases.GetPostsUC
import com.jherrera.domain.use_cases.GetUsersUC

class GetUsersInteractor(private val repository: HousecallProRepository)  {

    val getUsers: GetUsersUC
        get() = GetUsersUC(repository)

    val getPosts: GetPostsUC
        get() = GetPostsUC(repository)
}