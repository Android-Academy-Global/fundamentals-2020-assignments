package com.android.fundamentals.workshop02

import android.provider.BaseColumns

object LocationsContract {

    object LocationEntry {

        const val TABLE_NAME = "locations"

        const val COLUMN_NAME_ID = BaseColumns._ID

        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_LATITUDE = "latitude"
        const val COLUMN_NAME_LONGITUDE = "longitude"
    }
}