package com.jherrera.domain.usercases

import com.jherrera.domain.repositories.HousecallProRepository

class GetUsersUC(private val repository: HousecallProRepository) {
    suspend operator fun invoke() = repository.GetUsers()
}