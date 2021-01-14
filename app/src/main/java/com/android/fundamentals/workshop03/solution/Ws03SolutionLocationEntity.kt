package com.android.fundamentals.workshop03.solution

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.android.fundamentals.workshop03.Ws03DbContract

// TODO 01: Add "@Entity" annotation with params.
//  First is a "tableName" with value = Ws03DbContract.Locations.TABLE_NAME.
//  Second is a "indices" with array of "Index(name: String)" as value, [Index(Ws03DbContract.Locations.COLUMN_NAME_ID)].
@Entity(
	tableName = Ws03DbContract.Locations.TABLE_NAME,
	indices = [Index(Ws03DbContract.Locations.COLUMN_NAME_ID)]
)
data class Ws03SolutionLocationEntity(
	
	// TODO 02: Location model contains: id: Long, title: String, lat: Double, lon: Double.
	//  Add all these properties and mark each with annotation @ColumnInfo(name = String).
	//  Take names from contract "Ws03DbContract.Locations".
	
	// TODO 03: Mark "id" property as @PrimaryKey with param "autoGenerate = true".
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = Ws03DbContract.Locations.COLUMN_NAME_ID)
	val id: Long = 0,
	
	@ColumnInfo(name = Ws03DbContract.Locations.COLUMN_NAME_TITLE)
	val title: String,
	
	@ColumnInfo(name = Ws03DbContract.Locations.COLUMN_NAME_LATITUDE)
	val lat: Double,
	
	@ColumnInfo(name = Ws03DbContract.Locations.COLUMN_NAME_LONGITUDE)
	val lon: Double
)