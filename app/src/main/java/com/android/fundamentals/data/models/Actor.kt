package com.android.fundamentals.data.models

import android.net.Uri

data class Actor(
    val name: String,
    val avatar: Uri,
    val hasOscar: Boolean
)