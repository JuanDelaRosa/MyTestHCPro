package com.jherrera.data.repositories

import com.jherrera.domain.common.Result
import com.jherrera.domain.entities.User
import com.jherrera.domain.repositories.HousecallProRepository

class HousecallProRepositoryImpl(
    private val remoteDataSource: HousecallProRemoteDataSource):
    HousecallProRepository {
    override suspend fun GetUsers(): Result<List<User>> {
        return remoteDataSource.getUsers()
    }

    override suspend fun GetPosts(id: Int): Result<Int> {
        return remoteDataSource.getPosts(id)
    }
}