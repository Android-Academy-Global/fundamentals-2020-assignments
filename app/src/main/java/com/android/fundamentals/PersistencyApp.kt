package com.android.fundamentals

import android.app.Application
import com.android.fundamentals.workshop02.Workshop2LocationRepository

class PersistencyApp : Application() {

    val workshop2LocationRepository by lazy { Workshop2LocationRepository() }
}