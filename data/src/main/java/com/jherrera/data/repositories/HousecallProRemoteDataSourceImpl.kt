package com.jherrera.data.repositories

import com.jherrera.data.Exceptions
import com.jherrera.data.api.HousecallProService
import com.jherrera.data.mappers.HousecallProMapper
import com.jherrera.domain.entities.User
import com.jherrera.domain.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HousecallProRemoteDataSourceImpl(private val service: HousecallProService, private val mapper: HousecallProMapper) : HousecallProRemoteDataSource {
    override suspend fun getUsers(): Result<List<User>> {
        return withContext(Dispatchers.IO){
            try{
                val response = service.GetUsers()
                if(response.isSuccessful){
                    return@withContext Result.Success(mapper.toUsers(response.body()))
                }
                else
                    return@withContext Result.Error(Exception(Exceptions.NoInternet))
            }catch (e:Exception){
                return@withContext Result.Error(Exception(Exceptions.NoInternet))
            }
        }
    }
}