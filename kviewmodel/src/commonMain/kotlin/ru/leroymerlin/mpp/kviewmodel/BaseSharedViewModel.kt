package ru.leroymerlin.mpp.kviewmodel

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

public abstract class BaseSharedViewModel<State, Action, Event>(initialState: State) : KViewModel() {

    private val _viewStates = MutableStateFlow(initialState)

    private val _viewActions = MutableSharedFlow<Action?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    public fun viewStates(): StateFlow<State> = _viewStates.asStateFlow()

    public fun viewActions(): SharedFlow<Action?> = _viewActions.asSharedFlow()

    protected var viewState: State
        get() = _viewStates.value
        set(value) {
            _viewStates.value = value
        }

    protected var viewAction: Action?
        get() = _viewActions.replayCache.last()
        set(value) {
            _viewActions.tryEmit(value)
        }

    public abstract fun obtainEvent(viewEvent: Event)

}