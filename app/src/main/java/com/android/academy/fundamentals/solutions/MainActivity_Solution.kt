package com.android.academy.fundamentals.solutions

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.R
import com.android.academy.fundamentals.SecondActivity
import com.android.academy.fundamentals.solutions.SecondActivity_Solution.Companion.TRANSMITTED_BOOLEAN
import com.android.academy.fundamentals.solutions.SecondActivity_Solution.Companion.TRANSMITTED_INT
import com.android.academy.fundamentals.solutions.SecondActivity_Solution.Companion.TRANSMITTED_STRING

class MainActivity_Solution : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация переменной кнопки перехода
        val textView: TextView = findViewById(R.id.first_activity_text_view)
        // Добавление обработчика нажатия кнопки
        textView.setOnClickListener { moveToNextScreen() }
    }

    // Переход на следующий экран
    private fun moveToNextScreen() {
        // Создание интента перехода
        val intent = Intent(this, SecondActivity::class.java)

        // Добавление extra данных в интент
        val transmittedString = "string to transmit"
        intent.putExtra(TRANSMITTED_STRING, transmittedString)

        val transmittedInt = 12
        intent.putExtra(TRANSMITTED_INT, transmittedInt)

        val transmittedBoolean = false
        intent.putExtra(TRANSMITTED_BOOLEAN, transmittedBoolean)

        // Старт второй активити
        startActivity(intent)
    }
}
