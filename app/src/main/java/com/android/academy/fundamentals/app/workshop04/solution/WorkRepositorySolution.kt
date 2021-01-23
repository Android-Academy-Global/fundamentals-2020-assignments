package com.android.academy.fundamentals.app.workshop04.solution

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import java.util.concurrent.TimeUnit

class WorkRepositorySolution {
    //NotTodo 4.3: Create simple OneTimeWorkRequest
    val simpleRequest = OneTimeWorkRequest.Builder(MyWorkerSolution::class.java).build()
    //NotTodo 4.5: Create delayedRequest
    val delayedRequest = OneTimeWorkRequest.Builder(MyWorkerSolution::class.java)
        .setInitialDelay(10L, TimeUnit.SECONDS)
        .build()
    //NotTodo 4.7: Create constrainedRequest
    private val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    val constrainedRequest = OneTimeWorkRequest.Builder(MyWorkerSolution::class.java)
        .setConstraints(constraints)
        .build()
}