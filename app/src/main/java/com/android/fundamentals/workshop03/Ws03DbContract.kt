package com.android.fundamentals.workshop03

import android.provider.BaseColumns

object Ws03DbContract {
    
    const val DATABASE_NAME = "Locations_ws03.db"
    
    object Locations {
        const val TABLE_NAME = "locations_ws03"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_LATITUDE = "latitude"
        const val COLUMN_NAME_LONGITUDE = "longitude"
    }
}