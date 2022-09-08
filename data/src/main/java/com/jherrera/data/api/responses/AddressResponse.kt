package com.jherrera.data.api.responses

data class AddressResponse(
    val city: String?,
    val geo: GeoResponse?,
    val street: String?,
    val suite: String?,
    val zipcode: String?
)