/*
 * Copyright (C) 2020 The Android Open Source Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.people.data

typealias ChatThreadListener = (List<Message>) -> Unit

class Chat(val contact: Contact) {

    private val listeners = mutableListOf<ChatThreadListener>()

    var messages: List<Message> = listOf(
        Message(
            MessagesIds.getNextMessageId(),
            contact.id,
            "Send me a message",
            null,
            null,
            System.currentTimeMillis(),
            true
        ),
        Message(
            MessagesIds.getNextMessageId(),
            contact.id,
            "I will reply in 5 seconds",
            null,
            null,
            System.currentTimeMillis(),
            true
        ))
        private set(value) {
            field = value
            listeners.forEach { listener -> listener(value) }
        }

    fun addListener(listener: ChatThreadListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: ChatThreadListener) {
        listeners.remove(listener)
    }

    fun addMessage(message: Message) {
        messages = messages + listOf(message)
    }

    fun updateMessage(message: Message) {
        val index = messages.indexOfFirst { it.id == message.id }
        if (index != -1) {
            val updated = messages.toMutableList()
            updated[index] = message
            messages = updated
        }
    }

    fun markMessagesAsRead() {
        messages = messages.map {
            if (it.isNew) {
                it.copy(isNew = false)
            } else it
        }
    }

    fun removeMessage(messageId: Long) {
        val index = messages.indexOfFirst { it.id == messageId }
        if (index != -1) {
            val updated = messages.toMutableList()
            updated.removeAt(index)
            messages = updated
        }
    }
}
