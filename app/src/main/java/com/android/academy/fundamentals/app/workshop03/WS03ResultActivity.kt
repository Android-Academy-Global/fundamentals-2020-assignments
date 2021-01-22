package com.android.academy.fundamentals.app.workshop03

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.app.R
import com.bumptech.glide.Glide

class WS03ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws03_result)

        findViewById<ImageView>(R.id.image)?.apply {
            intent.extras?.getParcelable<Uri>(KEY_IMAGE_URI)?.let { uri ->
                Glide
                    .with(context)
                    .load(uri)
                    .into(this)
            }
        }
    }

    companion object {
        const val KEY_IMAGE_URI = "image_uri"
    }
}
