package ru.leroymerlin.mpp.compose
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import ru.leroymerlin.mpp.kviewmodel.KViewModel

@Composable
fun <T : KViewModel> ViewModel(
    factory: () -> T,
    content: @Composable (T) -> Unit
) {
    // Instantiate KViewModel to use
    val viewModel = remember { factory.invoke() }

    // Show content
    content(viewModel)

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clear()
        }
    }
}