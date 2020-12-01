package com.android.academy.fundamentals.workshop03.solution

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.*

class WS03CoroutinesFragment : Fragment(R.layout.fragment_coroutines_scope_cancel) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val startButton: Button
        get() = view?.findViewById(R.id.start_button)
            ?: throw IllegalStateException("No start button")

    private val stopButton: Button
        get() = view?.findViewById(R.id.stop_button)
            ?: throw IllegalStateException("No stop button")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startButton.setOnClickListener {
            startCoroutines(view)
        }

        stopButton.setOnClickListener {
            cancelCoroutines(view)
        }
    }

    private fun cancelCoroutines(view: View) {
        scope.cancel()

        view.findViewById<TextView>(R.id.first_coroutine_value).text = ""
        view.findViewById<TextView>(R.id.second_coroutine_value).text = ""
        view.findViewById<TextView>(R.id.third_coroutine_value).text = ""
        view.findViewById<TextView>(R.id.fourth_coroutine_value).text = ""

        startButton.isEnabled = true
        stopButton.isEnabled = false
    }

    private fun startCoroutines(view: View) {
        startButton.isEnabled = false
        stopButton.isEnabled = true

        scope.launch {
            runFirstCoroutine(view.findViewById(R.id.first_coroutine_value))
        }
        scope.launch {
            runSecondCoroutine(view.findViewById(R.id.second_coroutine_value))
        }
        GlobalScope.launch {
            runThirdCoroutine(view.findViewById(R.id.third_coroutine_value))
        }
        scope.launch {
            runFourthCoroutine()
        }
    }

    private suspend fun runFirstCoroutine(resultView: TextView) {
        var count = 0
        while (true) {
            count++
            showResult(count.toString(), resultView)
            delay(1_000)
        }
    }

    private suspend fun runSecondCoroutine(resultView: TextView) {
        var count = 0
        while (true) {
            count += 5
            showResult(count.toString(), resultView)
            delay(1_000)
        }
    }

    private suspend fun runThirdCoroutine(resultView: TextView) {
        var count = 0
        while (count < 100) {
            count += 13
            showResult(count.toString(), resultView)
            delay(1_000)
        }
        showResult("", resultView)
    }

    private suspend fun runFourthCoroutine() {
        delay(1_000)
        // throw IllegalStateException("Some exception")
    }

    private suspend fun showResult(text: String, resultView: TextView) {
        withContext(Dispatchers.Main) {
            resultView.text = text
        }
    }
}