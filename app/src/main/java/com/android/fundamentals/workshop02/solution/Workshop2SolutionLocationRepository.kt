package com.android.fundamentals.workshop02.solution

import android.content.ContentValues
import android.content.Context
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationRepository
import com.android.fundamentals.workshop02.LocationsContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

class Workshop2SolutionLocationRepository(
    appContext: Context
) : LocationRepository {

    private val defaultDispatcher: CoroutineDispatcher get() = Dispatchers.Default
    private val ioDispatcher: CoroutineDispatcher get() = Dispatchers.IO

    private val random = Random(10)

    private val dbHelper = Workshop2SolutionLocationsDbHelper(appContext)

    private val readableDatabase by lazy { dbHelper.readableDatabase }
    private val writableDatabase by lazy { dbHelper.writableDatabase }

    override suspend fun getAllLocations(): List<Location> = withContext(ioDispatcher) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            LocationsContract.LocationEntry.COLUMN_NAME_ID,
            LocationsContract.LocationEntry.COLUMN_NAME_TITLE,
            LocationsContract.LocationEntry.COLUMN_NAME_LATITUDE,
            LocationsContract.LocationEntry.COLUMN_NAME_LONGITUDE
        )

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${LocationsContract.LocationEntry.COLUMN_NAME_TITLE} DESC"

        readableDatabase.query(
            LocationsContract.LocationEntry.TABLE_NAME,   // The table to query
            projection, // The array of columns to return (pass null to get all)
            null,   // The columns for the WHERE clause
            null,   // The values for the WHERE clause
            null,   // don't group the rows
            null,   // don't filter by row groups
            sortOrder   // The sort order
        ).use { cursor ->

            val items = mutableListOf<Location>()
            while (cursor.moveToNext()) {
                items.add(
                    Location(
                        id = cursor.getLong(cursor.getColumnIndexOrThrow(LocationsContract.LocationEntry.COLUMN_NAME_ID)),
                        title = cursor.getString(cursor.getColumnIndexOrThrow(LocationsContract.LocationEntry.COLUMN_NAME_TITLE)),
                        latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(LocationsContract.LocationEntry.COLUMN_NAME_LATITUDE)),
                        longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(LocationsContract.LocationEntry.COLUMN_NAME_LONGITUDE))
                    )
                )
            }

            items
        }
    }

    override suspend fun addNewAndGetUpdated(): List<Location> {
        val request = generateNewLocationRequest()
        saveNewLocation(request)
        return getAllLocations()
    }

    override suspend fun deleteByIdAndGetUpdated(id: Long): List<Location> {
        deleteById(id)
        return getAllLocations()
    }

    private suspend fun generateNewLocationRequest(): NewLocationRequest =
        withContext(defaultDispatcher) {
            delay(DELAY_MILLIS)
            NewLocationRequest(
                latitude = random.nextDouble(),
                longitude = random.nextDouble(),
                title = "Title ${random.nextInt()}"
            )
        }

    private suspend fun saveNewLocation(request: NewLocationRequest) = withContext(ioDispatcher) {
        writableDatabase.beginTransaction()
        try {

            val values = ContentValues()
            values.put(LocationsContract.LocationEntry.COLUMN_NAME_TITLE, request.title)
            values.put(LocationsContract.LocationEntry.COLUMN_NAME_LATITUDE, request.latitude)
            values.put(LocationsContract.LocationEntry.COLUMN_NAME_LONGITUDE, request.longitude)

            writableDatabase.insert(
                LocationsContract.LocationEntry.TABLE_NAME,
                null,
                values
            )

            writableDatabase.setTransactionSuccessful()
        } finally {
            writableDatabase.endTransaction()
        }
    }

    private suspend fun deleteById(id: Long) = withContext(ioDispatcher) {
        writableDatabase.beginTransaction()
        try {
            // Define 'where' part of query.
            val selection = "${LocationsContract.LocationEntry.COLUMN_NAME_ID} = ?"
            // Specify arguments in placeholder order.
            val selectionArgs = arrayOf(id.toString())

            writableDatabase.delete(
                LocationsContract.LocationEntry.TABLE_NAME,
                selection,
                selectionArgs
            )

            writableDatabase.setTransactionSuccessful()
        } finally {
            writableDatabase.endTransaction()
        }
    }

    companion object {
        private const val DELAY_MILLIS: Long = 1_000
    }
}

private class NewLocationRequest(
    val latitude: Double,
    val longitude: Double,
    val title: String
)