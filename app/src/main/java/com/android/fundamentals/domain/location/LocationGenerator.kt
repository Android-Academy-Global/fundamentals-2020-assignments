package com.android.fundamentals.domain.location

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

class LocationGenerator(private val dispatcher: CoroutineDispatcher) {

    private val random = Random(10)

    suspend fun generateNewLocation(): Location =
        withContext(dispatcher) {
            delay(DELAY_MILLIS)
            Location(
                id = System.currentTimeMillis(),
                latitude = random.nextDouble(),
                longitude = random.nextDouble(),
                title = "Title ${random.nextInt()}"
            )
        }

    companion object {
        const val DELAY_MILLIS: Long = 2_000
    }
}