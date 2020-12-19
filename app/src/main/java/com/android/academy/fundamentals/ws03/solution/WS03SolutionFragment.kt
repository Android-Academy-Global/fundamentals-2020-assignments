package com.android.academy.fundamentals.ws03.solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS03SolutionFragment : Fragment() {
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		return inflater.inflate(R.layout.fragment_ws03, container, false)
	}
	
	companion object {
		fun create() = WS03SolutionFragment()
	}
}