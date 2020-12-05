package com.android.academy.fundamentals.workshop_2

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS02FirstCoroutineTaskFragment: Fragment(R.layout.fragment_ws_02) {

    // TODO(WS2:1) create scope (CoroutineScope) for future coroutines with context Dispatchers.Main
    // private val scope = ...

    private var textView: TextView? = null;
    private var button: Button? = null;
    private var scrollView: ScrollView? = null;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.tv)
        textView?.movementMethod = ScrollingMovementMethod()
        button = view.findViewById(R.id.button)
        scrollView = view.findViewById(R.id.scrollView)

        button?.setOnClickListener {
            // TODO(WS2:2) create coroutine - call readFromFile from scope using launch and launch the app
        }
    }

    // TODO(WS2:3) reading from file is a heavy work, it should not be done from the main thread
    //  let's add a context - Dispatcher to readFromFile

    // TODO(WS2:4) make readFromFile suspended.
    //  Add a keyword - suspend - to readFromFile function.
    //  Then launch the app
    private fun readFromFile() {
        val file = context?.resources?.openRawResource(R.raw.alice);
        file?.bufferedReader()
                ?.useLines { lines ->
                    lines.forEach {
                        updateTextView(it)
                    }
                }
    }

    // TODO(WS2:5) the app crashes because textView is updated not from the main thread (UI thread)
    //  you need to add context - Dispatchers.Main - and suspend to updateTextView so the view is updated from the main thread
    //  launch the app and check if the app works correctly
    private fun updateTextView(text: String) {
        textView?.append("\n$text")
        scrollView?.fullScroll(View.FOCUS_DOWN)
    }

    // TODO(WS2:6)* update class variable 'scope' from Dispatchers.Main to Dispatchers.Default
    //  then think and discuss with the team and your mentor what context readFromFile
    //  and updateTextView should have after the change
}
