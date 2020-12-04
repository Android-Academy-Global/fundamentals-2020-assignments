package com.android.academy.fundamentals.workshop_4

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

class WS04ErrorHandlingProblemFragment : Fragment() {

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

    // TODO 01: Create the CoroutineScope from Default dispatchers and common Job.
    private fun createScope(): CoroutineScope = TODO()

    // TODO 02: Create the CoroutineScope from Default dispatchers and Supervisor job.
    private fun createSuperScope(): CoroutineScope = TODO()

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

    // TODO: UI thread logger SAMPLE.
    private fun loggerSample() {
        try {
            coroutineScope.launch {  }

        } catch (throwable: Throwable) {
            logException("loggerSample", throwable)
        }
    }

    // TODO: Coroutine-To-UI thread logger SAMPLE.
    private fun suspendLoggerSample() {
        coroutineScope.launch {
            try {

            } catch (throwable: Throwable) {
                logExceptionSuspend("suspendLoggerSample", throwable)
            }
        }
    }

    // TODO 03: Run "methodWithException("failLaunchWithException")" inside a coroutine:
    //  Try to launch the coroutine from regular "coroutineScope".
    //  This will fail coroutine with Exception and close Application.
    //  Catch and Log exception with proper Logger method (you can't).
    private fun failLaunchWithException() {
        methodWithException("failLaunchWithException", coroutineScope.isActive)
    }

    // TODO 04: Run "methodWithException("workWithHandledException")" inside a coroutine:
    //  Launch coroutine from regular "coroutineScope".
    //  Try to run the method and Catch exception.
    //  Log exception with proper Logger method.
    private fun workWithHandledException() {
        methodWithException("workWithHandledException", coroutineScope.isActive)
    }

    // TODO 05: Create "val deferred" with "methodWithException("failAwaitWithException")" inside a deferred:
    //  Try to launch coroutine from regular "coroutineScope".
    //  Inside that coroutine:
    //  - create the deferred from regular "coroutineScope" and async builder.
    //  - await result.
    //  This will fail coroutine with Exception and close Application.
    //  Catch and Log exception with proper Logger method (you can't).
    private fun failAwaitWithException() {
        methodWithException("failAwaitWithException", coroutineScope.isActive)
    }

    // TODO 06: Create "val deferred" with "methodWithException("awaitWorkWithHandledException")" inside a deferred:
    //  Create the deferred from regular "coroutineScope" and async builder.
    //  Try to run the method and Catch exception inside a deferred.
    //  Log exception with proper Logger method.
    //  Launch coroutine from regular "coroutineScope".
    //  Await result inside.
    private fun awaitWorkWithHandledException() {
        methodWithException("awaitWorkWithHandledException", coroutineScope.isActive)
    }

    // TODO 07: Run "methodWithException("workWithExceptionHandler")" inside a coroutine:
    //  Launch coroutine from regular "coroutineScope".
    //  Add the "exceptionHandler" to the launch builder's coroutine context.
    //  Inspect the realization of the "exceptionHandler".
    //  Why we have to create another scope?
    private fun workWithExceptionHandler() {
        methodWithException("workWithExceptionHandler", coroutineScope.isActive)
    }

    // TODO 08: Run "methodWithException("superWorkWithExceptionHandler")" inside a coroutine:
    //  Launch coroutine from regular "coroutineSuperScope".
    //  Add the "superExceptionHandler" to the launch builder's coroutine context.
    //  Inspect the realization of the "superExceptionHandler".
    //  Why we don't have to create another scope?
    private fun superWorkWithExceptionHandler() {
        methodWithException("superWorkWithExceptionHandler", coroutineScope.isActive)
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