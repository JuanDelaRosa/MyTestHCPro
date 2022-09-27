package com.jherrera.data.repositories

import com.jherrera.domain.entities.User
import com.jherrera.domain.common.Result

interface HousecallProRemoteDataSource {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getPosts(id: Int) : Result<Int>
}