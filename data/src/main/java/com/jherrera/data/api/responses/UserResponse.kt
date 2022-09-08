package com.jherrera.data.api.responses

data class UserResponse(
    val address: AddressResponse?,
    val company: CompanyResponse?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val phone: String?,
    val username: String?,
    val website: String?
)