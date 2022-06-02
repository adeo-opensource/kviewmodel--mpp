package com.adeo.kviewmodel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

public class WrappedStateFlow<T : Any>(private val origin: StateFlow<T>) : StateFlow<T> by origin {
    public fun watch(block: (T) -> Unit): Closeable = watchFlow(block)
}

public class WrappedSharedFlow<T : Any?>(
    private val origin: SharedFlow<T>,
    private val onComplete: () -> Unit
) : SharedFlow<T> by origin {

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        currentCoroutineContext().job.invokeOnCompletion {
            onComplete.invoke()
        }
        origin.collect(collector)
    }

    public fun watch(block: (T) -> Unit): Closeable = watchFlow(block)
}

private fun <T> Flow<T>.watchFlow(block: (T) -> Unit): Closeable {
    val context = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    onEach(block).launchIn(context)

    return object : Closeable {
        override fun close() {
            context.cancel()
        }
    }
}