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

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.transition.TransitionInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.people.R
import com.example.android.people.VoiceCallActivity
import com.example.android.people.getNavigationController

/**
 * The chat screen. This is used in the full app (MainActivity) as well as in the expanded Bubble
 * (BubbleActivity).
 */
class ChatFragment : Fragment(R.layout.chat_fragment) {
    
    private var rvMessages: RecyclerView? = null
    private var etChatInput: ChatEditText? = null
    private var ivPhoto: ImageView? = null
    private var voiceCallButton: ImageButton? = null
    private var sendButton: ImageButton? = null
    
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        enterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.slide_bottom)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getLong(ARG_ID)
        if (id == null) {
            parentFragmentManager.popBackStack()
            return
        }
        val prepopulateText = arguments?.getString(ARG_PREPOPULATE_TEXT)
        val navigationController = getNavigationController()
        
        viewModel.setChatId(id)
        
        val messageAdapter = MessageAdapter(view.context) { uri ->
            navigationController.openPhoto(uri)
        }
        val linearLayoutManager = LinearLayoutManager(view.context).apply {
            stackFromEnd = true
        }
        
        rvMessages = view.findViewById<RecyclerView>(R.id.messagesRecycler).apply {
            layoutManager = linearLayoutManager
            adapter = messageAdapter
        }
        
        viewModel.contact.observe(viewLifecycleOwner) { contact ->
            if (contact == null) {
                Toast.makeText(view.context, "Contact not found", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
                
            } else {
                navigationController.updateAppBar { name, icon ->
                    name.text = contact.name
                    Glide
                        .with(this)
                        .load(contact.iconUri)
                        .circleCrop()
                        .into(icon)
                    startPostponedEnterTransition()
                }
            }
        }
        
        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            messageAdapter.submitList(messages) {
                linearLayoutManager.scrollToPosition(messages.size - 1)
            }
        }
        
        etChatInput = view.findViewById<ChatEditText>(R.id.input).apply {
            if (prepopulateText != null) {
                setText(prepopulateText)
            }
            
            setOnImageAddedListener { contentUri, mimeType, label ->
                viewModel.setPhoto(contentUri, mimeType)
                if (text.isNullOrBlank()) {
                    setText(label)
                }
            }
            
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    send(text)
                    true
                    
                } else {
                    false
                }
            }
        }
        
        ivPhoto = view.findViewById(R.id.photo)
        viewModel.photo.observe(viewLifecycleOwner) { uri ->
            if (uri == null) {
                ivPhoto?.visibility = View.GONE
                
            } else {
                ivPhoto?.apply {
                    visibility = View.VISIBLE
                    Glide.with(context).load(uri).into(this)
                }
            }
        }
        
        voiceCallButton = view.findViewById<ImageButton>(R.id.voice_call).apply {
            setOnClickListener {
                voiceCall()
            }
        }
        
        sendButton = view.findViewById<ImageButton>(R.id.send).apply {
            setOnClickListener {
                send(etChatInput?.text)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val foreground = arguments?.getBoolean(ARG_FOREGROUND) == true
        viewModel.foreground = foreground
    }

    override fun onStop() {
        super.onStop()
        viewModel.foreground = false
    }
    
    override fun onDestroyView() {
        clearViews()
        
        super.onDestroyView()
    }
    
    private fun clearViews() {
        rvMessages?.adapter = null
        rvMessages = null
        etChatInput = null
        ivPhoto = null
        voiceCallButton = null
        sendButton = null
    }

    private fun voiceCall() {
        val contact = viewModel.contact.value ?: return
        startActivity(
            Intent(requireActivity(), VoiceCallActivity::class.java)
                .putExtra(VoiceCallActivity.EXTRA_NAME, contact.name)
                .putExtra(VoiceCallActivity.EXTRA_ICON, contact.icon)
        )
    }
    
    private fun send(text: Editable?) {
        text?.let {
            if (it.isNotEmpty()) {
                viewModel.send(it.toString())
                it.clear()
            }
        }
    }
    
    companion object {
        private const val ARG_ID = "id"
        private const val ARG_FOREGROUND = "foreground"
        private const val ARG_PREPOPULATE_TEXT = "prepopulate_text"
        
        fun newInstance(id: Long, foreground: Boolean, prepopulateText: String? = null) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ID, id)
                    putBoolean(ARG_FOREGROUND, foreground)
                    putString(ARG_PREPOPULATE_TEXT, prepopulateText)
                }
            }
    }
}
