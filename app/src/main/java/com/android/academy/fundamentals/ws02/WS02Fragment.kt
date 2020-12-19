package com.android.academy.fundamentals.ws02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.academy.fundamentals.BaseFragment
import com.android.academy.fundamentals.R

class WS02Fragment : BaseFragment() {
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		return inflater.inflate(R.layout.fragment_ws02, container, false)
	}
	
	companion object {
		fun create() = WS02Fragment()
	}
}