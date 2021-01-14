package com.android.fundamentals.workshop03.solution

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.fundamentals.workshop03.Ws03DbContract

// TODO 10: Make this class abstract and extend from "RoomDatabase()".
//  Mark with @Database() annotation with "entities = [Ws03LocationEntity::class], version = 1" as params.
@Database(entities = [Ws03SolutionLocationEntity::class], version = 1)
abstract class Ws03SolutionDataBase : RoomDatabase() {

	// TODO 11: Listed all DAOs as abstract properties (we have only one "Ws03LocationsDao").
	abstract val locationsDao: Ws03SolutionLocationsDao
	
	companion object {
		
		// TODO 12: Add a function to create a DataBase with "application: Context" as param.
		//  It returns an instance of "Ws03DataBase".
		//  To create a DB, we have to create "Room.databaseBuilder" with params:
		//  (@NonNull Context context, @NonNull Class<T> klass, @NonNull String dbName).
		//  Provide "applicationContext", "Ws03DataBase::class.java" and "DATABASE_NAME" from the "Ws03DbContract".
		//  Add ".fallbackToDestructiveMigration()", build() the Builder.
		fun create(applicationContext: Context): Ws03SolutionDataBase = Room.databaseBuilder(
			applicationContext,
			Ws03SolutionDataBase::class.java,
			Ws03DbContract.DATABASE_NAME
		)
			.fallbackToDestructiveMigration()
			.build()
	}
}