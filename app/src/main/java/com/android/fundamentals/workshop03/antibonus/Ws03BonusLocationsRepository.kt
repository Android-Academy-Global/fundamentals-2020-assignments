package com.android.fundamentals.workshop03.antibonus

import androidx.lifecycle.LiveData
import com.android.fundamentals.domain.location.Location

interface Ws03BonusLocationsRepository {

    suspend fun getAllLocations(): List<Location>

    suspend fun addNewAndGetUpdated(): List<Location>

    suspend fun deleteByIdAndGetUpdated(id: Long): List<Location>
    
    fun getLocationsCount(): LiveData<Int>
}