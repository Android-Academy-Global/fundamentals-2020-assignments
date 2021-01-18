package com.android.fundamentals.workshop03.solution

import android.content.Context
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationRepository
import com.android.fundamentals.workshop02_03.NewLocationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

// TODO 04: Extend this class from "LocationRepository" and implement method stubs.

// TODO 05: Inspect methods to realize what we have to do with our DataBase.
//  * getAllLocations(): List<Location>					- select all locations from DB;
//  * addNewAndGetUpdated(): List<Location>				- insert one location and select all;
//  * deleteByIdAndGetUpdated(id: Long): List<Location>	- delete one location and select all;
class Ws03SolutionLocationsRepository(applicationContext: Context) : LocationRepository {
	
	private val random = Random(10)
	
	// TODO 13: Create a property with type of the "Ws03DataBase" and initialize here.
	//  Use function from "TODO_12".
	private val locationsDb = Ws03SolutionDataBase.create(applicationContext)
	
	
	// TODO 14: override suspend fun getAllLocations().
	//  Call this function "withContext(Dispatchers.IO)".
	//  Call LocationDao "getAll()" and use "toLocation(...)" to "map" list with Entities to list with Locations.
	override suspend fun getAllLocations(): List<Location> = withContext(Dispatchers.IO) {
		locationsDb.locationsDao.getAll().map { toLocation(it) }
	}
	
	// TODO 15: override suspend fun addNewAndGetUpdated().
	//  Call this function "withContext(Dispatchers.IO)".
	//  Create "NewLocationRequest" from "generateNewLocationRequest()".
	//  Convert "NewLocationRequest" to " Ws03LocationEntity" with "toEntity(...)".
	//  Pass the entity to the LocationDao "insert(...)".
	//  Call "getAllLocations()" in the end.
	override suspend fun addNewAndGetUpdated(): List<Location> = withContext(Dispatchers.IO) {
		val newLocationRequest = generateNewLocationRequest()
		locationsDb.locationsDao.insert(toEntity(newLocationRequest))
		getAllLocations()
	}
	
	// TODO 15: override suspend fun deleteByIdAndGetUpdated(id: Long).
	//  Call this function "withContext(Dispatchers.IO)".
	//  Call LocationDao "deleteById(id)" to delete location entry.
	//  Call "getAllLocations()" in the end.
	override suspend fun deleteByIdAndGetUpdated(id: Long): List<Location> =
		withContext(Dispatchers.IO) {
			locationsDb.locationsDao.deleteById(id)
			getAllLocations()
		}

	private fun toEntity(location: NewLocationRequest) = Ws03SolutionLocationEntity(
		title = location.title,
		lat = location.latitude,
		lon = location.longitude
	)

	private fun toLocation(entity: Ws03SolutionLocationEntity) = Location(
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