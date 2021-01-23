package com.android.academy.fundamentals.app.workshop04.solution

import android.content.Context
import android.util.Log
import androidx.work.*
import androidx.work.Worker
import com.android.academy.fundamentals.app.workshop04.ConnectionChecker

class MyWorkerSolution(context: Context, params: WorkerParameters): Worker(context, params){
    //NotTodo 4.1: Extend from Worker with Context&WorkerParameters
    //NotTodo 4.2: Override doWork() and write simple cycle for check internet connection with delay 1S

    override fun doWork(): Result {
        for (attempt in 0..10){
            Log.d("MyWorker", "Attempt to connect. Attempts: $attempt")
            Thread.sleep(1000)
            if(ConnectionChecker.isOnline()){
                return Result.success()
            }
        }
        return Result.failure()
    }
}