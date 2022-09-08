package com.jherrera.domain.repositories

import com.jherrera.domain.common.Result
import com.jherrera.domain.entities.User

interface HousecallProRepository {
    suspend fun GetUsers() : Result<List<User>>
}