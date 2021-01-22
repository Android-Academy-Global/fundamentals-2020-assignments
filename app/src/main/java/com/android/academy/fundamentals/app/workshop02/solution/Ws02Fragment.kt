package com.android.academy.fundamentals.app.workshop02.solution

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R

class Ws02Fragment : Fragment() {
	
	private var boundIndicatorView: TextView? = null
	private var reportView: TextView? = null
	private var bindButton: View? = null
	private var unbindButton: View? = null
	
	private var isBound = false
	private var service: Ws02BoundedService? = null
	private val serviceConnection: ServiceConnection = object : ServiceConnection {
		override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
			isBound = true
			boundIndicatorView?.isEnabled = true
			service = (binder as? Ws02BoundedService.Ws02Binder)?.getService()
			service?.observableData()?.observe(viewLifecycleOwner) { report ->
				reportView?.text = report
			}
			service?.startWork(hasRecognitionPermission = checkRecognitionPermission(requireContext()))
			Log.d(TAG, "onServiceConnected component:${name?.className}, iBinder:${binder?.javaClass}, service:${service?.javaClass}")
		}
		
		override fun onServiceDisconnected(name: ComponentName?) {
			Log.d(TAG, "onServiceDisconnected component:${name?.className}")
			isBound = false
			boundIndicatorView?.isEnabled = false
			service = null
		}
	}
	
	private var isRecognitionReceiverRegistered = false
	
	override fun onAttach(context: Context) {
		Log.d(TAG, "onAttach")
		super.onAttach(context)
	}
	
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
		
		setupViews(view)
		setupListeners()
		startBoundedService()
	}
	
	override fun onStart() {
		Log.d(TAG, "onStart")
		super.onStart()
	}
	
	override fun onResume() {
		Log.d(TAG, "onResume")
		super.onResume()
	}
	
	override fun onPause() {
		Log.d(TAG, "onPause")
		super.onPause()
	}
	
	override fun onStop() {
		Log.d(TAG, "onStop")
		unbindFromService()
		
		super.onStop()
	}
	
	override fun onDestroyView() {
		Log.d(TAG, "onDestroyView")
		stopBoundedService()
		clearViews()
		
		super.onDestroyView()
	}
	
	override fun onDestroy() {
		Log.d(TAG, "onDestroy")
		super.onDestroy()
	}
	
	override fun onDetach() {
		Log.d(TAG, "onDetach")
		super.onDetach()
	}
	
	private fun setupViews(parent: View) {
		boundIndicatorView = parent.findViewById(R.id.tvBoundIndicator)
		reportView = parent.findViewById(R.id.tvDeviceActivityReport)
		bindButton = parent.findViewById(R.id.btnBindService)
		unbindButton = parent.findViewById(R.id.btnUnbindService)
	}
	
	private fun setupListeners() {
		bindButton?.setOnClickListener {
			val hasPermission = checkRecognitionPermission(it.context)
			Log.d(TAG, "hasPermission:$hasPermission, isBound:$isBound")
			if (hasPermission && !isBound) {
				bindToService()
				
			} else {
				requestRecognitionPermission(requireActivity())
			}
		}
		
		unbindButton?.setOnClickListener { unbindFromService() }
	}
	
	private fun clearViews() {
		boundIndicatorView = null
		reportView = null
		bindButton = null
		unbindButton = null
	}
	
	private fun startBoundedService() {
		val res = context?.startService(getServiceIntent())
		Log.d(TAG, "startBoundedService res:${res?.className}")
	}
	
	private fun stopBoundedService() {
		val res = context?.stopService(getServiceIntent())
		Log.d(TAG, "stopBoundedService res:$res")
	}
	
	private fun bindToService() {
		val res = context?.bindService(getServiceIntent(), serviceConnection, Context.BIND_AUTO_CREATE)
		Log.d(TAG, "bindToService res:$res")
	}
	
	private fun unbindFromService() {
		Log.d(TAG, "unbindFromService isBound:$isBound")
		if (isBound) {
			isBound = false
			boundIndicatorView?.isEnabled = false
			context?.unbindService(serviceConnection)
		}
	}
	
	private fun getServiceIntent() = Intent(requireContext(), Ws02BoundedService::class.java)
	
	private fun checkRecognitionPermission(context: Context) = if (isGreaterThanQ()) {
		PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
			context,
			Manifest.permission.ACTIVITY_RECOGNITION
		)
		
	} else {
		true
	}
	
	private fun requestRecognitionPermission(activity: Activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
			ActivityCompat.requestPermissions(
				activity,
				arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
				PERMISSION_REQUEST_ACTIVITY_RECOGNITION
			)
		}
	}
	
	private fun isGreaterThanQ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
	
	companion object {
		private const val TAG = "WS02::FRAGMENT"
		private const val PERMISSION_REQUEST_ACTIVITY_RECOGNITION = 333
		
		fun create() = Ws02Fragment()
	}
}