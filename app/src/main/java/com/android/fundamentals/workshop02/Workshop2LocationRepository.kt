package com.android.fundamentals.workshop02

import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationRepository

class Workshop2LocationRepository : LocationRepository {

    override suspend fun getAllLocations(): List<Location> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewAndGetUpdated(): List<Location> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteByIdAndGetUpdated(id: Long): List<Location> {
        TODO("Not yet implemented")
    }
}