package com.android.academy.fundamentals

import android.util.Log
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseFragment : Fragment() {
	
	var coroutineScope = createCoroutineScope()
	
	fun getBaseUrl(): String = BuildConfig.BASE_URL
	
	fun getApiKey(): String =
		(context?.applicationContext as? KeyStore)?.getApiKey() ?: logInvalidApiKey()
	
	fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)
	
	private fun logInvalidApiKey(): String {
		Log.e("API:", "", IllegalArgumentException("Invalid api key error"))
		return "invalid api key"
	}
}