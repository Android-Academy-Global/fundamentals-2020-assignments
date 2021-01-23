package com.android.academy.fundamentals.app.workshop4.solution

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.work.WorkManager
import com.android.academy.fundamentals.app.R

class WS04FragmentSolution : Fragment(R.layout.fragment_ws04) {
    private val workRepository = WorkRepositorySolution()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //NotTodo 4.4: Enqueue simpleRequest with WorkManager
        //WorkManager.getInstance(requireContext()).enqueue(workRepository.simpleRequest)
        //NotTodo 4.6: Enqueue delayedRequest with WorkManager
        //WorkManager.getInstance(requireContext()).enqueue(workRepository.delayedRequest)
        //NotTodo 4.8: Enqueue constrainedRequest with WorkManager
        WorkManager.getInstance(requireContext()).enqueue(workRepository.constrainedRequest)
    }
    
    companion object {
        fun create() = WS04FragmentSolution()
    }
}
