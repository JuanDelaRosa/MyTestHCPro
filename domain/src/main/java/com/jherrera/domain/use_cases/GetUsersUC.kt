package com.jherrera.domain.use_cases

import com.jherrera.domain.repositories.HousecallProRepository

class GetUsersUC(private val repository: HousecallProRepository) {
    suspend operator fun invoke() = repository.GetUsers()
}