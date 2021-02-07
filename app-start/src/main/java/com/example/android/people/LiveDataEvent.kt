package com.example.android.people

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe

open class LiveDataEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

    fun handle(handler: (content: T) -> Unit) {
        val content = getContentIfNotHandled()
        if (content != null) {
            handler(content)
        }
    }
}

fun <T> LiveData<LiveDataEvent<T>>.handle(liveCycleOwner: LifecycleOwner, handler: (content: T) -> Unit) {
    this.observe(liveCycleOwner) { it.handle(handler) }
}

fun <T> MutableLiveData<LiveDataEvent<T>>.rise(event: T) {
    value = LiveDataEvent(event)
}