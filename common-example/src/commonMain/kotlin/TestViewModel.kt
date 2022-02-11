import adeo.kviewmodel.BaseSharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import models.TestAction
import models.TestEvent
import models.TestViewState

class TestViewModel: BaseSharedViewModel<TestViewState, TestAction, TestEvent>() {

    init {
        viewState = TestViewState()
    }

    override fun obtainEvent(viewEvent: TestEvent) {
        when (viewEvent) {
            is TestEvent.EventWithParam -> obtainEventWithParams(viewEvent.value)
            TestEvent.Launch -> launchHelloWorld()
        }
    }

    private fun launchHelloWorld() {
        viewState = viewState.copy(someText = "Hello, World")
    }

    private fun obtainEventWithParams(param: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                delay(2000)

                withContext(Dispatchers.Main) {
                    viewState = viewState.copy(someValue = param)
                }
            }
        }
        viewState = viewState.copy()
    }
}