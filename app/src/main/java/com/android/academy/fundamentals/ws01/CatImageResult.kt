package com.android.academy.fundamentals.ws01

import okhttp3.Headers

data class CatImageResult(
	val statusCode: Int = 0,
	val headers: Headers? = null,
	val message: String = "",
	val jsonResponse: String? = null
) {
	
	fun hasBadResult(): Boolean = jsonResponse.isNullOrEmpty()
			|| (statusCode < 200 || statusCode >= 300)
}
