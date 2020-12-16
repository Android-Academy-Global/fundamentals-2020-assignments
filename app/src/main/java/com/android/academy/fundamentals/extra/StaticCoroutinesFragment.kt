package com.android.academy.fundamentals.extra

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.*

class StaticCoroutinesFragment : Fragment(R.layout.fragment_static) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind(view.findViewById(R.id.static_result_view))

        start()
    }

    override fun onDestroyView() {
        if (activity?.isFinishing == true) stop()

        unbind()

        super.onDestroyView()
    }

    companion object {
        private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
        private var job: Job? = null
        private var resultView: TextView? = null

        fun start() {
            if (job == null) {
                job = scope.launch {
                    var counter = 0
                    while (true) {
                        delay(1_000)
                        counter += 1
                        resultView?.text = counter.toString()
                    }
                }
            }
        }

        fun stop() {
            job?.cancel()
        }

        fun bind(view: TextView) {
            resultView = view
        }

        fun unbind() {
            resultView = null
        }
    }
}