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

package com.example.android.people.ui.chat

import android.Manifest
import android.app.Application
import android.net.Uri
import androidx.annotation.RequiresPermission
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.android.people.LiveDataEvent
import com.example.android.people.R
import com.example.android.people.data.ChatRepository
import com.example.android.people.data.DefaultChatRepository
import com.example.android.people.data.FusedLocationProvider
import com.example.android.people.data.GetUserLocationResult
import com.example.android.people.data.LocationProvider
import com.example.android.people.rise
import kotlinx.coroutines.launch

class ChatViewModel @JvmOverloads constructor(
    application: Application,
    private val repository: ChatRepository = DefaultChatRepository.getInstance(application),
    private val locationProvider: LocationProvider = FusedLocationProvider(application)
) : AndroidViewModel(application) {

    private val chatId = MutableLiveData<Long>()

    private val _photoUri = MutableLiveData<Uri?>()
    val photo: LiveData<Uri?> = _photoUri

    private var _photoMimeType: String? = null

    private val _events = MutableLiveData<LiveDataEvent<Event>>()
    val events: LiveData<LiveDataEvent<Event>> = _events

    sealed class Event {
        object LocationProviderDisabled : Event()
        data class Error(val textResource: Int) : Event()
    }

    /**
     * We want to dismiss a notification when the corresponding chat screen is open. Setting this
     * to `true` dismisses the current notification and suppresses further notifications.
     *
     * We do want to keep on showing and updating the notification when the chat screen is opened
     * as an expanded bubble. [ChatFragment] should set this to false if it is launched in
     * BubbleActivity. Otherwise, the expanding a bubble would remove the notification and the
     * bubble.
     */
    var foreground = false
        set(value) {
            field = value
            chatId.value?.let { id ->
                if (value) {
                    repository.activateChat(id)
                } else {
                    repository.deactivateChat(id)
                }
            }
        }

    /**
     * The contact of this chat.
     */
    val contact = chatId.switchMap { id -> repository.findContact(id) }

    /**
     * The list of all the messages in this chat.
     */
    val messages = chatId.switchMap { id -> repository.findMessages(id) }

    fun setChatId(id: Long) {
        chatId.value = id
        if (foreground) {
            repository.activateChat(id)
        } else {
            repository.deactivateChat(id)
        }
    }

    fun send(text: String) {
        val id = chatId.value
        if (id != null && id != 0L) {
            repository.sendMessage(id, text, _photoUri.value, _photoMimeType)
        }
        _photoUri.value = null
        _photoMimeType = null
    }

    @RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun sendLocation() {
        viewModelScope.launch {
            when (val getLocation = locationProvider.getUserLocation()) {
                is GetUserLocationResult.Success -> {
                    val location = getLocation.location
                    val fixedAccuracy = (location.accuracy * 100).toInt() / 100
                    send(getApplication<Application>().getString(
                        R.string.ws04_message_with_location,
                        location.latitude,
                        location.longitude,
                        fixedAccuracy
                    ))
                }
                GetUserLocationResult.Failed.LocationProviderIsDisabled ->
                    _events.rise(Event.LocationProviderDisabled)
                GetUserLocationResult.Failed.LocationManagerNotAvailable ->
                    _events.rise(Event.Error(R.string.ws04_location_manager_not_available_text))
                GetUserLocationResult.Failed.OtherFailure ->
                    _events.rise(Event.Error(R.string.ws04_error_getting_location))
            }
        }
    }

    fun setPhoto(uri: Uri, mimeType: String) {
        _photoUri.value = uri
        _photoMimeType = mimeType
    }

    override fun onCleared() {
        chatId.value?.let { id -> repository.deactivateChat(id) }
    }
}
