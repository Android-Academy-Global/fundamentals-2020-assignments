package com.android.academy.fundamentals.workshop2

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.delay

class WS05FirstCoroutineFragment: Fragment(R.layout.coroutines_workshop_02) {

    // TODO: WS05_1 создай scope (CoroutineScope) для будущих корутин с контекстом Dispatchers.Main
    // private val scope = ...

    private var textView: TextView? = null;
    private var button: Button? = null;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.tv)
        textView?.movementMethod = ScrollingMovementMethod()
        button = view.findViewById(R.id.button)

        button?.setOnClickListener {
            // TODO: WS05_2 создай корутину - вызови readFromFile из scope используя launch
        }
    }

    // TODO: WS05_3 delay в readFromFile является suspendable функцией, поэтому сделаем readFromFile suspendable
    // добавь "suspend" к функции readFromFile
    // запусти приложение, нажми на кнопку, чтобы загрузить текст, нажми несколько раз на экран
    // и посмотри, что произойдет
    // (появился ли ANR диалог?)

    // TODO: WS05_4 чтениие из файла - тяжелая операция, которая не должна делаться в главном потоке
    // поэтому нужно добавить отдельный контекст - Dispatcher ко всей функции
    // но какой выбрать? т.к. чтение из файла это Input/output операция - нужно добавить Dispatchers.IO
    // затем запусти приложение и посмотри, что произойдет
    private fun readFromFile() {
        val file = context?.resources?.openRawResource(R.raw.alice);
        file?.bufferedReader()
            ?.useLines { lines ->
                lines.forEach {
                    updateTextView(it)
                    delay(100)
                } }
    }

    // TODO: WS05_5 приложение падает, потому что textView обновляется не из главного потока (UI потока)
    // нужно добавить контекст Dispatchers.Main к этой фунции, чтобы обновление вью происходило из главного потока
    // после этого запусти приложение и посмотри, все ли работает в этот раз
    private fun updateTextView(text: String) {
        textView?.append("\n$text")
    }

    // TODO: WS05_6* обнови scope с Dispatchers.Main на Dispatchers.Default
    // затем подумай и обсуди с группой и ментором, какие контексты должны быть у
    // readFromFile и updateTextView после изменения контекста у scope
}
