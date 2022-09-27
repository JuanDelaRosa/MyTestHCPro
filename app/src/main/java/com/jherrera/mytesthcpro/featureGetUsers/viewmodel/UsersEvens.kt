package com.jherrera.mytesthcpro.featureGetUsers.viewmodel

sealed class UsersEvens

data class OpenPhone(val phoneNumber: String) : UsersEvens()
data class OpenEmail(val email: String) : UsersEvens()
data class OpenWebsite(val website: String) : UsersEvens()
data class OpenMap(val lat: String, val lng: String, val street: String) : UsersEvens()