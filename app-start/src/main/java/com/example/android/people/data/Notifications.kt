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

import android.app.Notification
import android.content.Context
import androidx.annotation.WorkerThread
import androidx.core.app.NotificationManagerCompat

/**
 * Handles all operations related to [Notification].
 */
interface Notifications {
    fun initialize()
    fun showNotification(chat: Chat)
    fun dismissNotification(chatId: Long)
}

class AndroidNotifications(private val context: Context) : Notifications {

    companion object {
        /**
         * The notification channel for messages. This is used for showing Bubbles.
         */
        private const val CHANNEL_NEW_MESSAGES = "new_messages"

        private const val REQUEST_CONTENT = 1

        private const val CHAT_TAG = "chat"
    }

    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    override fun initialize() {
        if (notificationManagerCompat.getNotificationChannel(CHANNEL_NEW_MESSAGES) == null) {
            TODO(
                """
                
                #1 Create The Notification Channel
                 
                a) Create a `NotificationChannel` object:
                   Create `NotificationChannelCompat.Builder`
                   with `CHANNEL_NEW_MESSAGES` as channel ID and `IMPORTANCE_HIGH` as 
                        a channel importance
                   use `R.string.channel_new_messages` as channel name
                   use `R.string.channel_new_messages_description` as channel description
                 
                b) Create a notification channel in the Notification manager:
                   Pass built `NotificationChannelCompat` to the 
                     `notificationManagerCompat.createNotificationChannel`
                     
                Note: Don't forget we have context in the class already
                
                """.trimIndent()
            )
        }
    }

    @WorkerThread
    override fun showNotification(chat: Chat) {
        TODO(
            """
                
                #2 Show The Notification
                
                a) Create the notification:
                   Create `NotificationCompat.Builder`
                   with `CHANNEL_NEW_MESSAGES` as channel ID
                   use `chat.contact.name` as Group
                   use `chat.contact.name` as Content Title
                   use last message text from chat.messages as Content Text
                   use `R.drawable.ic_message` as Small Icon
                   use `IMPORTANCE_HIGH` as Priority
                   use last message timestamp as When
                   
                b) Show notification:
                   Pass built notification to `notificationManagerCompat.notify`
                   use `CHAT_TAG` as notification Tag
                   use `chat.contact.id` as notification ID
                   
            """.trimIndent()
        )

        TODO(
            """
                
                #4 Open Chat From The Notification
                
                a) Form deep link (`contentUri`): 
                   Use string pattern `https://android.example.com/chat/$chat.contact.id`
                   Use Android KTX method `String.toUri()` to convert it to Uri
                 
                b) Create Chat Intent (`chatIntent`):
                   Create explicit intent for `MainActivity` class
                   Set action view `Intent.ACTION_VIEW`
                   Set `contentUri` as data
                   
                c) Create a Pending Intent (`pendingIntent`):
                   Use `PendingIntent.getActivity()`
                   Use `REQUEST_CONTENT` as Request Code
                   Use `chatIntent` as Intent
                   Use `PendingIntent.FLAG_UPDATE_CURRENT` as Flags
                   
                d) Pass pending intent to the notification builder:
                   Pass `pendingIntent` to builder with `setContentIntent()` setter
                
            """.trimIndent()
        )
    }

    override fun dismissNotification(chatId: Long) {
        TODO(
            """
                
                #3 Cancel Chat Notification(s)
                
                Simply call `notificationManagerCompat.cancel` with `CHAT_TAG` as tag and `chatId` as 
                    notification ID
                    
            """.trimIndent()
        )
    }
}
