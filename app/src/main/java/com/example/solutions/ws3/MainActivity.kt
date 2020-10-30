package com.example.solutions.ws3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.solutions.R

class MainActivity : AppCompatActivity() {

    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    companion object {
        const val TRANSMITTED_STRING = "transmittedString"
        const val TRANSMITTED_INT = "transmittedInt"
        const val TRANSMITTED_BOOLEAN = "transmittedBoolean"
    }

}