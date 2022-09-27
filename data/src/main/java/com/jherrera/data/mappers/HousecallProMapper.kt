package com.jherrera.data.mappers

import com.jherrera.data.api.responses.*
import com.jherrera.domain.entities.Address
import com.jherrera.domain.entities.Company
import com.jherrera.domain.entities.Geo
import com.jherrera.domain.entities.User

class HousecallProMapper {

    fun toUsers(response: List<UserResponse>?): List<User> {
        return response?.let { users ->
            users.map { user ->
                getUser(user)
            }
        } ?: emptyList()
    }

    fun countPosts(response: List<PostResponse>?): Int {
        return response?.size ?: 0
    }

    private fun getUser(user: UserResponse): User {
        return User(
            address = getAddress(user.address),
            company = getCompany(user.company),
            email = user.email ?: "",
            id = user.id ?: 0,
            name = user.name ?: "",
            phone = user.phone ?: "",
            username = user.username ?: "",
            website = user.website ?: "")
    }

    private fun getCompany(company: CompanyResponse?): Company {
        return Company(
            bs = company?.bs ?: "",
            catchPhrase = company?.catchPhrase ?: "",
            name = company?.name ?: ""
        )
    }

    private fun getAddress(address: AddressResponse?): Address {
        return Address(
            city = address?.city ?: "",
            geo = getGeo(address?.geo),
            street = address?.street ?: "",
            suite = address?.suite ?: "",
            zipcode = address?.zipcode ?: ""
        )
    }

    private fun getGeo(geo: GeoResponse?): Geo {
        return Geo(
            lat = geo?.lat ?: "",
            lng = geo?.lng ?: ""
        )
    }
}