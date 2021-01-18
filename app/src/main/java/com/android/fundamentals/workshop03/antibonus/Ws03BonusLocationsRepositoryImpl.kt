package com.android.fundamentals.workshop03.antibonus

import android.content.Context
import androidx.lifecycle.LiveData
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.workshop02_03.NewLocationRequest
import com.android.fundamentals.workshop03.solution.Ws03SolutionDataBase
import com.android.fundamentals.workshop03.solution.Ws03SolutionLocationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

class Ws03BonusLocationsRepositoryImpl(applicationContext: Context) : Ws03BonusLocationsRepository {
	
	private val random = Random(10)

	private val locationsDb = Ws03SolutionDataBase.create(applicationContext)

	override suspend fun getAllLocations(): List<Location> = withContext(Dispatchers.IO) {
		locationsDb.locationsDao.getAll().map { toLocation(it) }
	}
	
	override suspend fun addNewAndGetUpdated(): List<Location> = withContext(Dispatchers.IO) {
		val newLocationRequest = generateNewLocationRequest()
		locationsDb.locationsDao.insert(toEntity(newLocationRequest))
		getAllLocations()
	}

	override suspend fun deleteByIdAndGetUpdated(id: Long): List<Location> =
		withContext(Dispatchers.IO) {
			locationsDb.locationsDao.deleteById(id)
			getAllLocations()
		}
	
	override fun getLocationsCount(): LiveData<Int> = locationsDb.locationsDao.getLocationsCount()

	private fun toEntity(location: NewLocationRequest) = Ws03SolutionLocationEntity(
		title = location.title,
		lat = location.latitude,
		lon = location.longitude
	)

	private fun toLocation(entity: Ws03SolutionLocationEntity): Location = Location(
		id = entity.id,
		title = "${entity.title}, id:${entity.id}",
		latitude = entity.lat,
		longitude = entity.lon
	)

	private suspend fun generateNewLocationRequest() = withContext(Dispatchers.Default) {
		delay(DELAY_MILLIS)
		NewLocationRequest(
			title = "Title ${random.nextInt()}",
			latitude = random.nextDouble(180.0),
			longitude = random.nextDouble(180.0)
		)
	}

	companion object {
		private const val DELAY_MILLIS: Long = 1_000
	}
}