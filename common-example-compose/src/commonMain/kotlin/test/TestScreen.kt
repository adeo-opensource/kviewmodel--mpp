package test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.leroymerlin.mpp.compose.ViewModel
import ru.leroymerlin.mpp.compose.collectAsState
import test.models.TestAction
import test.models.TestEvent

@Composable
fun TestScreen() {
    ViewModel(factory = { TestViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().collectAsState()
        val viewAction = viewModel.viewActions().collectAsState()

        TestView(
            text = viewState.value.someText,
            value = viewState.value.someValue,
            onButtonClick = { viewModel.obtainEvent(TestEvent.EventWithParam(value = 5)) }
        )

        when (val action = viewAction.value) {
            is TestAction.OpenDetail -> {
                // screen switching depends on the navigation library
                // DetailScreen(action.param)
            }
        }
    }
}

@Composable
private fun TestView(
    text: String,
    value: Int,
    onButtonClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = text)
        Text(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp), text = value.toString())
        Button(onClick = {
            onButtonClick.invoke()
        }) {
            Text("Click to send")
        }
    }
}