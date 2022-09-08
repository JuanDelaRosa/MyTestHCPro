package com.jherrera.mytesthcpro.app

import android.content.Context
import com.jherrera.data.api.NetworkModule
import com.jherrera.data.mappers.HousecallProMapper
import com.jherrera.data.repositories.HousecallProRemoteDataSourceImpl
import com.jherrera.data.repositories.HousecallProRepositoryImpl
import com.jherrera.domain.repositories.HousecallProRepository

object ServiceLocator {
    @Volatile
    var repository: HousecallProRepository? = null

    fun provideRepository(context: Context): HousecallProRepository {
        synchronized(this) {
            return repository ?: createRepository(context)
        }
    }

    private val networkModule by lazy {
        NetworkModule()
    }

    private fun createRepository(context: Context): HousecallProRepository {
        val newRepository = HousecallProRepositoryImpl(
            HousecallProRemoteDataSourceImpl(
                networkModule.createHousecallProAPI("https://jsonplaceholder.typicode.com/"),
                HousecallProMapper()
            )
        )
        repository = newRepository
        return newRepository
    }
}