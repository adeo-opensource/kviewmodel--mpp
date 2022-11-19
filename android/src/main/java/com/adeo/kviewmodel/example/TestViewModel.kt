package com.adeo.kviewmodel.example

import androidx.lifecycle.viewModelScope
import com.adeo.kviewmodel.example.models.TestAction
import com.adeo.kviewmodel.example.models.TestEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.adeo.kviewmodel.example.models.TestViewState
import com.adeo.kviewmodel.jetpack.BaseSharedViewModel

class TestViewModel: BaseSharedViewModel<TestViewState, TestAction, TestEvent>(initialState = TestViewState()) {

    init {
        // use if some data is not available at the initialization time
        // and need to perform some long operation
        // e.g: network request, database request
        fetchSomeInitialData()
    }

    override fun obtainEvent(viewEvent: TestEvent) = when (viewEvent) {
        is TestEvent.IncrementClick -> updateCounter(+1)
        is TestEvent.DecrementClick -> updateCounter(-1)
        is TestEvent.DetailClick -> viewAction = TestAction.OpenDetail(viewState.counter)
    }

    private fun fetchSomeInitialData() {
        viewState = viewState.copy(titleText = "Hello, World")
    }

    private fun updateCounter(byValue: Int) {
        viewModelScope.launch {
            val newValue = withContext(Dispatchers.Default) {
                delay(1000)
                return@withContext viewState.counter + byValue
            }
            viewState = viewState.copy(counter = newValue)
        }
    }
}