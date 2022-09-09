package com.jherrera.domain.entities

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    fun getAddress() : String{
        return "${address.suite} ${address.street}\n${address.city}, ${address.zipcode}"
    }
    fun getPhoneNumber() : String{
        return phone.replace("x",";")
    }
}