package com.android.academy.fundamentals.app.workshop02.solution

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.app.R

class Ws02SolutionFragment : Fragment() {
	
	private var boundIndicatorView: TextView? = null
	private var reportView: TextView? = null
	private var bindButton: View? = null
	private var unbindButton: View? = null
	
	private var isBound = false
	private var service: Ws02SolutionBoundedService? = null
	private val serviceConnection: ServiceConnection = object : ServiceConnection {
		override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
			isBound = true
			boundIndicatorView?.isEnabled = true
			service = (binder as? Ws02SolutionBoundedService.Ws02Binder)?.getService()
			service?.observableData()?.observe(viewLifecycleOwner) { report ->
				reportView?.text = report
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
		bindButton = null
		unbindButton = null
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
	
	private fun getServiceIntent() = Intent(requireContext(), Ws02SolutionBoundedService::class.java)
	
	companion object {
		private const val TAG = "WS02::FRAGMENT"
		
		fun create() = Ws02SolutionFragment()
	}
}