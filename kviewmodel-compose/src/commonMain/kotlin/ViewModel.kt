import adeo.kviewmodel.KViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

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
