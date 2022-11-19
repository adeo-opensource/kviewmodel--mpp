package com.adeo.kviewmodel.example

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.example.models.TestAction
import com.adeo.kviewmodel.example.models.TestEvent

@Composable
fun TestScreen(viewModel: TestViewModel) {
    val viewState = viewModel.viewStates().observeAsState()
    val viewAction = viewModel.viewActions().observeAsState()

    TestView(
        title = viewState.value.titleText,
        counter = viewState.value.counter,
        onIncrementClick = { viewModel.obtainEvent(TestEvent.IncrementClick) },
        onDecrementClick = { viewModel.obtainEvent(TestEvent.DecrementClick) },
        onDetailClick = { viewModel.obtainEvent(TestEvent.DetailClick) }
    )

    when (val action = viewAction.value) {
        is TestAction.OpenDetail -> {
            // screen switching depends on the navigation library
            // DetailScreen(action.param)
        }
        else -> {

        }
    }
}

@Composable
private fun TestView(
    title: String,
    counter: Int,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
    onDetailClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)

        Spacer(modifier = Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(onClick = onDecrementClick) {
                Text(text = "-")
            }
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = counter.toString(),
                fontSize = 26.sp
            )
            OutlinedButton(onClick = onIncrementClick) {
                Text(text = "+")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onDetailClick) {
            Text(text = "Close")
        }
    }
}