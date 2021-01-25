package com.example.android.people.data

sealed class ChatUpdate {
    data class NewMessage(val message: Message) : ChatUpdate()
    data class UpdateMessage(val message: Message) : ChatUpdate()
    data class RemoveMessage(val messageId: Long) : ChatUpdate()
}