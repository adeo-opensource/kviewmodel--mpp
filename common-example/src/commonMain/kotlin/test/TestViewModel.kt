package test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import test.models.TestAction
import test.models.TestEvent
import test.models.TestViewState
import ru.leroymerlin.mpp.kviewmodel.BaseSharedViewModel

class TestViewModel: BaseSharedViewModel<TestViewState, TestAction, TestEvent>(initialState = TestViewState()) {

    init {
        fetchSomeInitialData()
    }

    override fun obtainEvent(viewEvent: TestEvent) = when (viewEvent) {
        is TestEvent.EventWithParam -> obtainEventWithParams(viewEvent.value)
        is TestEvent.DetailClick -> viewAction = TestAction.OpenDetail(viewState.someValue)
    }

    private fun fetchSomeInitialData() {
        viewState = viewState.copy(someText = "Hello, World")
    }

    private fun obtainEventWithParams(param: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                delay(2000)
                return@withContext param + 1
            }
            viewState = viewState.copy(someValue = result)
        }
    }
}