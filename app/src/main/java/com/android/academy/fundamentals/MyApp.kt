package com.android.academy.fundamentals

import android.app.Application

class MyApp : Application(), KeyStore {
	
	private lateinit var apiKey: String
	
	override fun onCreate() {
		super.onCreate()
		
		initApi()
	}
	
	override fun getApiKey(): String = apiKey
	
	private fun initApi() {
		apiKey = "753009e5-1ad5-44dc-9cc0-ae43b4c6f8ce"
	}
}