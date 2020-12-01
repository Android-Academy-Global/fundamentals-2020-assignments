package com.android.academy.fundamentals.workshop03.solution

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.*

class WS03CoroutinesFragment : Fragment(R.layout.fragment_coroutines_scope_cancel) {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default + exceptionHandler)

    private var startButton: Button? = null
    private var stopButton: Button? = null
    private var firstCoroutineResultView: TextView? = null
    private var secondCoroutineResultView: TextView? = null
    private var thirdCoroutineResultView: TextView? = null
    private var fourthCoroutineResultView: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cacheViews(view)

        startButton?.setOnClickListener {
            startCoroutines()
        }

        stopButton?.setOnClickListener {
            cancelCoroutines()
        }
    }

    private fun cacheViews(view: View) {
        startButton = view.findViewById(R.id.start_button)
        stopButton = view.findViewById(R.id.stop_button)
        firstCoroutineResultView = view.findViewById(R.id.first_coroutine_value)
        secondCoroutineResultView = view.findViewById(R.id.second_coroutine_value)
        thirdCoroutineResultView = view.findViewById(R.id.third_coroutine_value)
        fourthCoroutineResultView = view.findViewById(R.id.fourth_coroutine_value)
    }

    private fun cancelCoroutines() {
        scope.cancel()

        firstCoroutineResultView?.text = getString(R.string.done)
        secondCoroutineResultView?.text = getString(R.string.done)
        thirdCoroutineResultView?.text = getString(R.string.done)
        fourthCoroutineResultView?.text = getString(R.string.done)

        startButton?.isEnabled = true
        stopButton?.isEnabled = false
    }

    private fun startCoroutines() {
        startButton?.isEnabled = false
        stopButton?.isEnabled = true

        scope.launch {
            runFirstCoroutine()
        }
        scope.launch {
            runSecondCoroutine()
        }
        GlobalScope.launch {
            runThirdCoroutine()
        }
        scope.launch {
            runFourthCoroutine()
        }
    }

    private suspend fun runFirstCoroutine() {
        var count = 0
        while (true) {
            count++
            showResult(count.toString(), firstCoroutineResultView)
            delay(1_000)
        }
    }

    private suspend fun runSecondCoroutine() {
        var count = 0
        while (true) {
            count += 5
            showResult(count.toString(), secondCoroutineResultView)
            delay(1_000)
        }
    }

    private suspend fun runThirdCoroutine() {
        var count = 0
        while (count < 100) {
            count += 13
            showResult(count.toString(), thirdCoroutineResultView)
            delay(1_000)
        }
        showResult(getString(R.string.finally_done), thirdCoroutineResultView)
    }

    private suspend fun runFourthCoroutine() {
        delay(1_000)
        throw IllegalStateException("Some exception")
    }

    private suspend fun showResult(text: String, resultView: TextView?) {
        withContext(Dispatchers.Main) {
            resultView?.text = text
        }
    }

    override fun onDestroyView() {
        clearCachedViews()
        super.onDestroyView()
    }

    private fun clearCachedViews() {
        startButton = null
        stopButton = null
        firstCoroutineResultView = null
        secondCoroutineResultView = null
        thirdCoroutineResultView = null
        fourthCoroutineResultView = null
    }
}