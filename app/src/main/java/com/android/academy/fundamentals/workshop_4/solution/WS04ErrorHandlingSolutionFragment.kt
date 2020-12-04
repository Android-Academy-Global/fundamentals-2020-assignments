package com.android.academy.fundamentals.workshop_4.solution

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.StringBuilder

class WS04ErrorHandlingSolutionFragment : Fragment(R.layout.fragment_ws_04) {

    private var fallByLaunchButton: Button? = null
    private var fallByAwaitButton: Button? = null
    private var handleWithTryCatchButton: Button? = null
    private var handleAwaitWithTryCatchButton: Button? = null
    private var handleWithHandler: Button? = null
    private var handleWithSuperHandler: Button? = null
    private var exceptionLogView: TextView? = null

    private val exceptionHandler = CoroutineExceptionHandler { canceledContext, exception ->
        val isActive = coroutineScope.isActive
        Log.e(TAG, "ExceptionHandler [Scope active:$isActive, canceledContext:$canceledContext]")
        coroutineScope = createScope().apply {
            launch {
                sb.append("exceptionHandler scope active:$isActive\n")
                logExceptionSuspend("exceptionHandler", exception)
            }
        }
    }

    private val superExceptionHandler = CoroutineExceptionHandler { canceledContext, exception ->
        val isActive = coroutineSupervisorScope.isActive
        Log.e(TAG, "SuperExceptionHandler [Scope active:$isActive, canceledContext:$canceledContext]")
        coroutineSupervisorScope.launch {
            sb.append("superExceptionHandler scope active:$isActive\n")
            logExceptionSuspend("superExceptionHandler", exception)
        }
    }

    // TODO 01
    private fun createScope() = CoroutineScope(Dispatchers.Default + Job())

    // TODO 02
    private fun createSuperScope() = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private var coroutineScope = createScope()
    private var coroutineSupervisorScope = createSuperScope()
    private val sb: StringBuilder = StringBuilder()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        cancelCoroutines()
        clearViews()
    }

    // TODO 03
    private fun failLaunchWithException() {
        try {
            coroutineScope.launch {
                // Will fail on Dispatchers.Default, not Dispatchers.IO
                methodWithException("failLaunchWithException", coroutineScope.isActive)
            }

        } catch (throwable: Throwable) {
            // Will not catch this. See logcat.
            logException("failLaunchWithException::Recovered", throwable)
        }
    }

    // TODO 04
    private fun workWithHandledException() {
        coroutineScope.launch {
            try {
                methodWithException("workWithHandledException", coroutineScope.isActive)

            } catch (throwable: Throwable) {
                logExceptionSuspend("workWithHandledException", throwable)
            }
        }
    }

    // TODO 05
    private fun failAwaitWithException() {
        try {
            coroutineScope.launch {
                val deferred = coroutineScope.async {
                    methodWithException("failAwaitWithException", coroutineScope.isActive)
                }

                deferred.await()
            }

        } catch (throwable: Throwable) {
            // Will not catch this. See logcat.
            logException("failAwaitWithException::Recovered", throwable)
        }
    }

    // TODO 06
    private fun awaitWorkWithHandledException() {
        val deferred = coroutineScope.async {
            try {
                methodWithException("awaitWorkWithHandledException", coroutineScope.isActive)

            } catch (throwable: Throwable) {
                logExceptionSuspend("awaitWorkWithHandledException", throwable)
            }
        }

        coroutineScope.launch {
            deferred.await()
        }
    }

    // TODO 07
    private fun workWithExceptionHandler() {
        coroutineScope.launch(exceptionHandler) {
            methodWithException("workWithExceptionHandler", coroutineScope.isActive)
        }
    }

    // TODO 08
    private fun superWorkWithExceptionHandler() {
        coroutineSupervisorScope.launch(superExceptionHandler) {
            methodWithException("superWorkWithExceptionHandler", coroutineScope.isActive)
        }
    }

    private fun methodWithException(who: String, isActive: Boolean) {
        sb.clear().append("scope isActive:$isActive")
                .append("\n")
                .append("$who::Started")
                .append("\n")

        throw IOException()
    }

    private suspend fun logExceptionSuspend(who: String, throwable: Throwable) = withContext(Dispatchers.Main) {
        logException(who, throwable)
    }

    private fun logException(who: String, throwable: Throwable) {
        Log.e(TAG, "$who::Failed", throwable)
        sb.append("$who::Recovered")
        exceptionLogView?.text = getString(
                R.string.ws04_exception_log_text,
                sb.toString(),
                throwable
        )
    }

    private fun setupViews(view: View) {
        fallByLaunchButton = view.findViewById(R.id.btnFallByLaunch)
        fallByAwaitButton = view.findViewById(R.id.btnFallByAwait)
        handleWithTryCatchButton = view.findViewById(R.id.btnHandleWithTryCatch)
        handleAwaitWithTryCatchButton = view.findViewById(R.id.btnHandleAwaitWithTryCatch)
        handleWithHandler = view.findViewById(R.id.btnHandleWithHandler)
        handleWithSuperHandler = view.findViewById(R.id.btnHandleWithSuperHandler)
        exceptionLogView = view.findViewById(R.id.tvExceptionLogText)
    }

    private fun clearViews() {
        fallByLaunchButton = null
        fallByAwaitButton = null
        handleWithTryCatchButton = null
        handleAwaitWithTryCatchButton = null
        handleWithHandler = null
        handleWithSuperHandler = null
        exceptionLogView = null
    }

    private fun setupListeners() {
        fallByLaunchButton?.setOnClickListener { failLaunchWithException() }
        fallByAwaitButton?.setOnClickListener { failAwaitWithException() }
        handleWithTryCatchButton?.setOnClickListener { workWithHandledException() }
        handleAwaitWithTryCatchButton?.setOnClickListener { awaitWorkWithHandledException() }
        handleWithHandler?.setOnClickListener { workWithExceptionHandler() }
        handleWithSuperHandler?.setOnClickListener { superWorkWithExceptionHandler() }
    }

    private fun cancelCoroutines() {
        coroutineScope.cancel("It's time")
        coroutineSupervisorScope.cancel("It's time")
    }
}

private const val TAG = "WS04"