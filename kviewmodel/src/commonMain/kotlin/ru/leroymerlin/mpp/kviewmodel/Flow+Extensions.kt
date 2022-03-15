package ru.leroymerlin.mpp.kviewmodel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

public fun <T> Flow<T>.watch(block: (T) -> Unit): Closeable {
    val context = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    onEach(block).launchIn(context)

    return object : Closeable {
        override fun close() {
            context.cancel()
        }
    }
}