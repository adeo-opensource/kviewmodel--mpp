package com.adeo.kviewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*

public fun <T : Any> WrappedStateFlow<T>.watch(block: (T) -> Unit): Closeable = watchFlow(block)

public fun <T : Any?> WrappedSharedFlow<T>.watch(block: (T) -> Unit): Closeable = watchFlow(block)

private fun <T> Flow<T>.watchFlow(block: (T) -> Unit): Closeable {
    val context = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    onEach(block).launchIn(context)

    return object : Closeable {
        override fun close() {
            context.cancel()
        }
    }
}