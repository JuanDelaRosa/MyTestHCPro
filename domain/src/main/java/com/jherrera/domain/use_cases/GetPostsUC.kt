package com.jherrera.domain.use_cases

import com.jherrera.domain.repositories.HousecallProRepository

class GetPostsUC(private val repository: HousecallProRepository) {
    suspend operator fun invoke(id: Int) = repository.GetPosts(id)
}