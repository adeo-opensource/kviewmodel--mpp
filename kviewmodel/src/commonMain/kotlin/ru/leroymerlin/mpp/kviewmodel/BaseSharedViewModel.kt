package ru.leroymerlin.mpp.kviewmodel

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class ViewModelNotInitializedException(override val message: String?) : Exception()

abstract class BaseSharedViewModel<State, Action, Event> : KViewModel() {

    private val TAG = BaseSharedViewModel::class.simpleName
    private val _viewStates: MutableStateFlow<State?> = MutableStateFlow(null)
    fun viewStates(): CFlow<State?> = _viewStates.wrap()

    protected var viewState: State
        get() = _viewStates.value
            ?: throw ViewModelNotInitializedException("\"viewState\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewStates.value == value) {
                _viewStates.value = null
            }

            _viewStates.value = value
        }

    private val _viewActions: MutableSharedFlow<Action?> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun viewActions(): CFlow<Action?> = _viewActions.wrap()

    protected var viewAction: Action?
        get() = _viewActions.replayCache.last()
        set(value) {
            _viewActions.tryEmit(value)
        }

    abstract fun obtainEvent(viewEvent: Event)

}