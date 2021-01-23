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

import androidx.core.net.toUri
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class Contact(
    val id: Long,
    val name: String,
    val icon: String,
    private val replyStrategy: Contact.(text: String) -> Flow<ChatUpdate>
) {

    companion object {
        val CONTACTS = listOf(
            Contact(1L, "Cat", "cat.jpg") {
                flow {
                    emit(ChatUpdate.NewMessage(buildReply().apply { this.text = "Meow" }.build()))
                }
            },
            Contact(2L, "Dog", "dog.jpg") {
                flow {
                    val message = buildReply().apply { this.text = "Woooooof woof!!" }.build()
                    emit(ChatUpdate.NewMessage(message))
                    delay(2000)
                    val updatedMessage = buildReply().apply {
                        this.text = "Woof woof!!"
                        this.id = message.id
                    }.build()
                    emit(ChatUpdate.UpdateMessage(updatedMessage))
                }
            },
            Contact(3L, "Parrot", "parrot.jpg") { mesageText ->
                flow {
                    emit(
                        ChatUpdate.NewMessage(
                            buildReply().apply { this.text = mesageText }.build()
                        )
                    )
                    delay(1000)
                    val messageToDelete1 = buildReply().apply { this.text = "co" }.build()
                    emit(ChatUpdate.NewMessage(messageToDelete1))
                    delay(1000)
                    val messageToDelete2 = buildReply().apply { this.text = "co-co" }.build()
                    emit(ChatUpdate.NewMessage(messageToDelete2))
                    delay(1000)
                    emit(ChatUpdate.RemoveMessage(messageToDelete2.id))
                    delay(1000)
                    emit(ChatUpdate.RemoveMessage(messageToDelete1.id))
                }
            },
            Contact(4L, "Sheep", "sheep.jpg") {
                flow {
                    emit(
                        ChatUpdate.NewMessage(buildReply().apply {
                            this.text = "Look at me!"
                            photo =
                                "content://com.example.android.people/photo/sheep_full.jpg".toUri()
                            photoMimeType = "image/jpeg"
                        }.build())
                    )
                }
            }
        )
    }

    val iconUri = "content://com.example.android.people/icon/$id".toUri()

    val shortcutId = "contact_$id"

    fun buildReply() = Message.Builder().apply {
        sender = this@Contact.id
        timestamp = System.currentTimeMillis()
        id = MessagesIds.getNextMessageId()
    }

    fun reply(text: String): Flow<ChatUpdate> = replyStrategy(text)
}
