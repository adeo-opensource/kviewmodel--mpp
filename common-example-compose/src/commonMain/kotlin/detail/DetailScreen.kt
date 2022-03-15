package detail

import androidx.compose.runtime.Composable
import ru.leroymerlin.mpp.compose.ViewModel

@Composable
fun DetailScreen(param: Int) {
    ViewModel(factory = { DetailViewModel(param) }) { _ ->

    }
}