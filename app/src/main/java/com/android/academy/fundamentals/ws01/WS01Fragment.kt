package com.android.academy.fundamentals.ws01

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.academy.fundamentals.BaseFragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class WS01Fragment : BaseFragment() {
	
	private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
		Log.e(TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
		coroutineScope = createCoroutineScope()
	}
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		return inflater.inflate(R.layout.fragment_ws01, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		loadImage()
	}
	
	private fun loadImage() {
		coroutineScope.launch(exceptionHandler) {
		}
	}
	
	companion object {
		private val TAG = WS01Fragment::class.java.simpleName
		
		fun create() = WS01Fragment()
	}
}