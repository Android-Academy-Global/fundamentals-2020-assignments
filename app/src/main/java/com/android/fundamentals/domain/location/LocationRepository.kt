package com.android.fundamentals.domain.location

interface LocationRepository {

    suspend fun getAllLocations(): List<Location>

    suspend fun addNewAndGetUpdated(): List<Location>

    suspend fun deleteByIdAndGetUpdated(id: Long): List<Location>
}