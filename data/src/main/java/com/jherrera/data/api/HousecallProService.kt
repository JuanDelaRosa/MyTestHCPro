package com.jherrera.data.api

import com.jherrera.data.api.responses.PostResponse
import com.jherrera.data.api.responses.UserResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface HousecallProService {
    @GET("users")
    suspend fun GetUsers() : Response<List<UserResponse>>
    @GET("users/{id}/posts")
    suspend fun GetPosts(@Path("id") id : Int) : Response<List<PostResponse>>
}