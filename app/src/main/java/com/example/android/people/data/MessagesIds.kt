package com.example.android.people.data

import java.util.concurrent.atomic.AtomicLong

internal object MessagesIds {

    private val currentMessageId = AtomicLong(0)

    fun getNextMessageId(): Long {
        val nextMessageId = currentMessageId.addAndGet(1)
        return nextMessageId
    }
}