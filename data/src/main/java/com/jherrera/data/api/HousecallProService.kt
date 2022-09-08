package com.jherrera.data.api

import com.jherrera.data.api.responses.UserResponse
import retrofit2.http.GET
import retrofit2.Response

interface HousecallProService {
    @GET("users")
    suspend fun GetUsers() : Response<List<UserResponse>>
}