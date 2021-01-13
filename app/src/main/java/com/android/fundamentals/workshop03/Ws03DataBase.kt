package com.android.fundamentals.workshop03

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.fundamentals.workshop03.Ws03DbContract

// TODO 10: Make this class abstract and extend from "RoomDatabase()".
//  Mark with @Database() annotation with "entities = [Ws03LocationEntity::class], version = 1" as params.
class Ws03DataBase {

	// TODO 11: Listed all DAOs as abstract properties (we have only one "Ws03LocationsDao").
	
	companion object {
		
		// TODO 12: Add a function to create a DataBase with "application: Context" as param.
		//  It returns an instance of "Ws03DataBase".
		//  To create a DB, we have to create "Room.databaseBuilder" with params:
		//  (@NonNull Context context, @NonNull Class<T> klass, @NonNull String dbName).
		//  Provide "applicationContext", "Ws03DataBase::class.java" and "DATABASE_NAME" from the "Ws03DbContract",
		//  build() the Builder.
	}
}