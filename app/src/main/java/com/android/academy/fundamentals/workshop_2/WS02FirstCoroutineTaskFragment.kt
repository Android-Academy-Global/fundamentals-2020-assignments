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

    // TODO(WS2:1) создай scope (CoroutineScope) для будущих корутин с контекстом Dispatchers.Main
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
            // TODO(WS2:2) создай корутину - вызови readFromFile из scope используя launch и запусти приложение
        }
    }

    // TODO(WS2:3) чтение из файла - тяжелая операция, которая не должна делаться в главном потоке
    //  поэтому нужно добавить отдельный контекст - Dispatcher ко всей функции readFromFile

    // TODO(WS2:4) сделаей readFromFile suspended.
    //  Для этого нужно добавить ключевое слово в сингатуру метода - suspend.
    //  затем запусти приложение
    private fun readFromFile() {
        val file = context?.resources?.openRawResource(R.raw.alice);
        file?.bufferedReader()
            ?.useLines { lines ->
                lines.forEach {
                    updateTextView(it)
                }
            }
    }

    // TODO(WS2:5) приложение падает, потому что textView обновляется не из главного потока (UI потока)
    //  нужно добавить контекст Dispatchers.Main и suspend к этой фунции, чтобы обновление вью происходило из главного потока
    //  после этого запусти приложение и посмотри, все ли работает в этот раз
    private fun updateTextView(text: String) {
        textView?.append("\n$text")
        scrollView?.fullScroll(View.FOCUS_DOWN)
    }

    // TODO(WS2:6)* обнови переменную класса scope с Dispatchers.Main на Dispatchers.Default
    //  затем подумай и обсуди с группой и ментором, какие контексты должны быть у
    //  readFromFile и updateTextView после изменения контекста у scope
}
