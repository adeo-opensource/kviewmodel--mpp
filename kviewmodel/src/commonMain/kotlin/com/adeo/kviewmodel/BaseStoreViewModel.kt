package com.adeo.kviewmodel

import com.adeo.kviewmodel.KViewModel
import com.adeo.kviewmodel.WrappedSharedFlow
import com.adeo.kviewmodel.WrappedStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class BaseStoreViewModel<State : Any, Action, Event>(initialState: State) : KViewModel() {

    private val _viewStates = MutableStateFlow(initialState)

    private val _viewActions =
        MutableSharedFlow<Action?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    fun viewStates(): WrappedStateFlow<State> = WrappedStateFlow(_viewStates.asStateFlow())

    fun viewActions(): WrappedSharedFlow<Action?> = WrappedSharedFlow(_viewActions.asSharedFlow())

    private var viewState: State
        get() = _viewStates.value
        set(value) {
            _viewStates.value = value
        }

    protected fun obtainAction(action: Action) {
        _viewActions.tryEmit(action)
    }

    private val lock = Mutex()

    fun obtainEvent(event: Event) {
        runBlocking(viewModelScope.coroutineContext) {
            lock.withLock {
                logEvent?.invoke(event)
                val state = reduce(event, viewState)
                viewState = state
                logState?.invoke(state)
            }
        }
    }

    abstract fun reduce(event: Event, oldState: State): State

    protected var logEvent: ((event: Event) -> Unit)? = null
    protected var logState: ((state: State) -> Unit)? = null


    /**
     * Convenient method to perform work in [viewModelScope] scope.
     */
    protected fun withViewModelScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = block)
    }
}
