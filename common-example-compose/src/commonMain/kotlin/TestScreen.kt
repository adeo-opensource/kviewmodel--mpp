import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.TestEvent
import models.TestViewState

@Composable
fun TestScreen() {

    ViewModel(factory = { TestViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()

        viewState.value?.let { state ->
            TestView(state) {
                viewModel.obtainEvent(TestEvent.EventWithParam(value = 5))
            }
        }

        LaunchedEffect(Unit) {
            viewModel.obtainEvent(TestEvent.Launch)
        }
    }
}

@Composable
fun TestView(viewState: TestViewState, onButtonClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = viewState.someText)
        Text(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp), text = viewState.someValue.toString())
        Button(onClick = {
            onButtonClick.invoke()
        }) {
            Text("Click to send")
        }
    }
}