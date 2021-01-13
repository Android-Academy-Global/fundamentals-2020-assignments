package com.android.fundamentals.workshop03

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// TODO 01: Add "@Entity" annotation with params.
//  First is a "tableName" with value = Ws03DbContract.Locations.TABLE_NAME.
//  Second is a "indices" with array of "Index(name: String)" as value, [Index(Ws03DbContract.Locations.COLUMN_NAME_ID)].
data class Ws03LocationEntity(
	
	// TODO 02: Location model contains: id: Long, title: String, lat: Double, lon: Double.
	//  Add all these properties and mark each with annotation @ColumnInfo(name = String).
	//  Take names from contract "Ws03DbContract.Locations".
	
	// TODO 03: Mark "id" property as @PrimaryKey with param "autoGenerate = true".
	val id: Long = 0,

	val title: String,

	val lat: Double,

	val lon: Double
)