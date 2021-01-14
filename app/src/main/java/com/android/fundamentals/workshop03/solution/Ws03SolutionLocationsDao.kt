package com.android.fundamentals.workshop03.solution

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// TODO 06: Add "Dao" annotation.
@Dao
interface Ws03SolutionLocationsDao {

	// TODO 07: Create functions to meet our Repository demands.
	//  Our DAO doesn't work with UI "Location" class but with Entity class.
	//  Add suspend function get all locations return List<Ws03LocationEntity>
	//  Add @Query annotation with "SELECT * FROM locations_ws03 ORDER BY _id ASC" as param.
	@Query("SELECT * FROM locations_ws03 ORDER BY _id ASC")
	suspend fun getAll(): List<Ws03SolutionLocationEntity>
	
	// TODO 08: Add suspend function insert "location: Ws03LocationEntity".
	//  Add @Insert annotation.
	@Insert
	suspend fun insert(location: Ws03SolutionLocationEntity)
	
	// TODO 09: Add suspend function delete by id "id: Long"
	//  Add @Query annotation with "DELETE FROM locations_ws03 WHERE _id == :id" as param.
	@Query("DELETE FROM locations_ws03 WHERE _id == :id")
	suspend fun deleteById(id: Long)
}