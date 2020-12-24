package com.android.academy.fundamentals

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseFragment : Fragment() {
	
	var coroutineScope = createCoroutineScope()
	
	override fun onDetach() {
		coroutineScope.cancel("It's time")
		
		super.onDetach()
	}
	
	fun getBaseUrl(): String = BuildConfig.BASE_URL
	
	fun getApiKey(): String = apiKey
	
	fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)
}

const val apiKey = "753009e5-1ad5-44dc-9cc0-ae43b4c6f8ce"