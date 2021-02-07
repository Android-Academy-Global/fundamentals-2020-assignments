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

package com.example.android.people

import android.content.Context
import android.net.Uri
import android.os.Bundle
import com.example.android.people.data.ChatRepository
import com.example.android.people.data.DefaultChatRepository

/**
 * Handles the "Reply" action in the chat notification.
 */
// Extend `ReplyReceiver` from `BroadcastReceiver` class
class ReplyReceiver {

    companion object {
        const val KEY_TEXT_REPLY = "reply"
    }
    // Override `onReceive` method
    // Get `result` from remoteInput via `RemoteInput.getResultsFromIntent(intent) ?: return`
    // Get `reply uri` from intent via `intent.data ?: return`
    // Call `updateNotification` method to send reply message

    private fun updateNotification(
        result: Bundle,
        contentUri: Uri,
        context: Context) {
        val repository: ChatRepository = DefaultChatRepository.getInstance(context)
        val input = result.getCharSequence(KEY_TEXT_REPLY)?.toString()
        val chatId = contentUri.lastPathSegment?.toLong() ?: return

        if (chatId > 0 && !input.isNullOrBlank()) {
            repository.sendMessage(chatId, input.toString(), null, null)
            // We should update the notification so that the user can see that the reply has been
            // sent.
            repository.updateNotification(chatId)
        }
    }
}

