package com.android.academy.fundamentals.ws01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.academy.fundamentals.BaseFragment
import com.android.academy.fundamentals.R

class WS01Fragment : BaseFragment() {
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		return inflater.inflate(R.layout.fragment_ws01, container, false)
	}
	
	companion object {
		fun create() = WS01Fragment()
	}
}