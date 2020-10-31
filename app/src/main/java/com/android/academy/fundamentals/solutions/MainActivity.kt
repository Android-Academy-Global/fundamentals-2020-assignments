package com.android.academy.fundamentals.solutions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.academy.fundamentals.R

class MainActivity : AppCompatActivity() {

    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //
        setContentView(R.layout.activity_main)

        // Инициализация переменной кнопки перехода
        textView = findViewById(R.id.first_activity_text_view)
        // Добавление обработчика нажатия кнопки
        textView?.setOnClickListener {
            moveToNextScreen()
        }
    }

    // Переход на следующий экран
    fun moveToNextScreen() {
    }

}
