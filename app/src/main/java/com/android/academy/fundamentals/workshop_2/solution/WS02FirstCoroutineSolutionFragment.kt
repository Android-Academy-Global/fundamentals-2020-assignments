package com.android.academy.fundamentals.workshop_2.solution

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.*

class WS02FirstCoroutineSolutionFragment : Fragment(R.layout.fragment_ws_02) {

    private val scope = CoroutineScope(Dispatchers.Main)

    private var textView: TextView? = null;
    private var button: Button? = null;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.tv)
        textView?.movementMethod = ScrollingMovementMethod()
        button = view.findViewById(R.id.button)

        button?.setOnClickListener { scope.launch { readFromFile() } }
    }

    private suspend fun readFromFile() = withContext(Dispatchers.IO) {
        val file = context?.resources?.openRawResource(R.raw.alice);
        file?.bufferedReader()
            ?.useLines { lines ->
                lines.forEach {
                    updateTextView(it)
                    delay(100)
                } }
    }

    private suspend fun updateTextView(text: String) = withContext(Dispatchers.Main) {
        textView?.append("\n$text")
    }
}
