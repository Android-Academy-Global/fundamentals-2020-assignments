package com.android.academy.fundamentals.app.workshop02.solution

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Configuration
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R

class Ws02SolutionFragment : Fragment() {
	
	private var boundIndicatorView: TextView? = null
	private var reportView: TextView? = null
	private var compassImage: ImageView? = null
	private var bindButton: View? = null
	private var unbindButton: View? = null
	
	private var orientationState = Configuration.ORIENTATION_PORTRAIT
	private var isPortrait = true
		get() =  orientationState == Configuration.ORIENTATION_PORTRAIT
	
	private var isBound = false
	private var service: Ws02SolutionBoundedService? = null
	private val serviceConnection: ServiceConnection = object : ServiceConnection {
		override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
			isBound = true
			boundIndicatorView?.isEnabled = true
			service = (binder as? Ws02SolutionBoundedService.Ws02Binder)?.getService()
			service?.observableData()?.observe(viewLifecycleOwner) { angles ->
				updateViews(angles)
			}
			Log.d(TAG, "onServiceConnected component:${name?.className}, iBinder:${binder?.javaClass}, service:${service?.javaClass}")
		}
		
		override fun onServiceDisconnected(name: ComponentName?) {
			Log.d(TAG, "onServiceDisconnected component:${name?.className}")
			isBound = false
			boundIndicatorView?.isEnabled = false
			service = null
		}
	}
	
	private var currentAzimuth = 0f
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		Log.d(TAG, "onCreateView")
		return inflater.inflate(R.layout.fragment_ws02, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		Log.d(TAG, "onViewCreated")
		super.onViewCreated(view, savedInstanceState)
		
		detectOrientation()
		setupViews(view)
		setupListeners()
	}
	
	override fun onStart() {
		Log.d(TAG, "onStart")
		super.onStart()
	}
	
	override fun onStop() {
		Log.d(TAG, "onStop")
		unbindFromService()
		
		super.onStop()
	}
	
	override fun onDestroyView() {
		Log.d(TAG, "onDestroyView")
		clearViews()
		
		super.onDestroyView()
	}
	
	override fun onDestroy() {
		Log.d(TAG, "onDestroy")
		super.onDestroy()
	}
	
	private fun detectOrientation() {
		orientationState = resources.configuration.orientation
		Log.d(TAG, "detectOrientation isPortrait:$isPortrait")
	}
	
	private fun setupViews(parent: View) {
		boundIndicatorView = parent.findViewById(R.id.tvBoundIndicator)
		reportView = parent.findViewById(R.id.tvDeviceActivityReport)
		compassImage = parent.findViewById(R.id.ivCompass)
		bindButton = parent.findViewById(R.id.btnBindService)
		unbindButton = parent.findViewById(R.id.btnUnbindService)
		
		compassImage?.isVisible = isPortrait
	}
	
	private fun setupListeners() {
		bindButton?.setOnClickListener {
			Log.d(TAG, "isBound:$isBound")
			if (!isBound) {
				bindToService()
			}
		}
		
		unbindButton?.setOnClickListener { unbindFromService() }
	}
	
	private fun clearViews() {
		boundIndicatorView = null
		reportView = null
		compassImage = null
		bindButton = null
		unbindButton = null
	}
	
	private fun bindToService() {
		val res = context?.bindService(getServiceIntent(), serviceConnection, Context.BIND_AUTO_CREATE)
		Log.d(TAG, "bindToService res:$res")
	}
	
	private fun unbindFromService() {
		Log.d(TAG, "unbindFromService isBound:$isBound")
		if (!isBound) return
		
		isBound = false
		boundIndicatorView?.isEnabled = false
		context?.unbindService(serviceConnection)
	}
	
	// https://www.javacodegeeks.com/2013/09/android-compass-code-example.html
	private fun updateViews(angles: FloatArray) {
		val azimuth = angles[0].toDegrees()
		
		if (isPortrait) {
			RotateAnimation(
				currentAzimuth,
				-azimuth,
				Animation.RELATIVE_TO_SELF,
				0.5f,
				Animation.RELATIVE_TO_SELF,
				0.5f
			).apply {
				duration - 300
				fillAfter = true
			}.let { compassImage?.startAnimation(it) }
		}
		
		reportView?.text = getString(
			R.string.ws02_activity_report_text,
			azimuth,
			angles[1].toDegrees(),
			angles[2].toDegrees()
		)
		
		currentAzimuth = -azimuth
	}
	
	private fun getServiceIntent() = Intent(requireContext(), Ws02SolutionBoundedService::class.java)
	
	private fun Float.toDegrees() = (Math.toDegrees(this.toDouble()) * 100).toInt() / 100f
	
	companion object {
		private const val TAG = "WS02::FRAGMENT"
		
		fun create() = Ws02SolutionFragment()
	}
}