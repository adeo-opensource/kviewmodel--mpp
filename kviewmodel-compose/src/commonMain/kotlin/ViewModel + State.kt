import adeo.kviewmodel.CFlow
import adeo.kviewmodel.Closeable
import androidx.compose.runtime.*

@Composable
fun <T> CFlow<T>.observeAsState(initial: T? = null): State<T?> {
    val state = remember { mutableStateOf(initial) }
    val flow = this
    var observer: Closeable? = null

    LaunchedEffect(Unit) {
        observer = flow.watch {
            state.value = it
        }
    }

    DisposableEffect(this) {
        onDispose {
            observer?.close()
        }
    }

    return state
}