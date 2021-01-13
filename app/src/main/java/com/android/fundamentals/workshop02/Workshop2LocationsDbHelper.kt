package com.android.fundamentals.workshop02

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Workshop2LocationsDbHelper(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        /** TODO 01: here you should create database with tables.
         * SQL-request string for creating table is [SQL_CREATE_ENTRIES].
         * To execute this SQL-request call [SQLiteDatabase.execSQL] on [db] instance and pass [SQL_CREATE_ENTRIES] as parameter: [db.execSQL(SQL_CREATE_ENTRIES)].
         */
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /** TODO 02: here you should handle upgrading database.
         * We will not care about any data stored in DB.
         * So we can drop all and recreate empty database.
         * SQL-request string for clearing tables is [SQL_DELETE_ENTRIES].
         * To execute this SQL-request call [SQLiteDatabase.execSQL] on [db] instance and pass [SQL_DELETE_ENTRIES] as parameter: [db.execSQL(SQL_DELETE_ENTRIES)].
         * Now to recreate all we can just call same SQL-request that was used in [onCreate],
         * but as we already have it inside [onCreate], let just call [onCreate].
         */
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Workshop2LocationsDbHelper.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${LocationsContract.LocationEntry.TABLE_NAME} " +
                    "(" +
                    "${LocationsContract.LocationEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${LocationsContract.LocationEntry.COLUMN_NAME_TITLE} TEXT," +
                    "${LocationsContract.LocationEntry.COLUMN_NAME_LATITUDE} REAL," +
                    "${LocationsContract.LocationEntry.COLUMN_NAME_LONGITUDE} REAL" +
                    ")"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${LocationsContract.LocationEntry.TABLE_NAME}"
    }
}