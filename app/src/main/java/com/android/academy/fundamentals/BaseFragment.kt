package com.android.academy.fundamentals

import android.util.Log
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
	
	fun getBaseUrl(): String = BuildConfig.BASE_URL
	
	fun getApiKey(): String =
		(context?.applicationContext as? KeyStore)?.getApiKey() ?: logInvalidApiKey()
	
	private fun logInvalidApiKey(): String {
		Log.e("API:", "", IllegalArgumentException("Invalid api key error"))
		return "invalid api key"
	}
}