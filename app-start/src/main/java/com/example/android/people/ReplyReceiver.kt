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

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
            // Create `chatRepository` via `DefaultChatRepository.getInstance(context)`
            // Get `value` from remoteInput via `RemoteInput.getResultsFromIntent(intent) ?: return`
            // Get user `reply message` from `value` via `results.getCharSequence(KEY_TEXT_REPLY)?.toString()`
            // Get `reply uri` from intent via `intent.data ?: return`
            // Parse replied chat id from `reply uri` via `uri.lastPathSegment?.toLong() ?: return`
            // Check if chatId > 0 AND reply `reply message` TRUE
            // Send `replied message` with `repository.sendMessage(chatId, input.toString(), null, null)`
            // And update notification with ` repository.updateNotification(chatId)`
}
