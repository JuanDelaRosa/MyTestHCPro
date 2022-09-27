package com.jherrera.mytesthcpro.app

import android.app.Application
import com.jherrera.domain.repositories.HousecallProRepository

class HousecallProApp: Application() {

    val repository: HousecallProRepository
        get() = ServiceLocator.provideRepository()
}


