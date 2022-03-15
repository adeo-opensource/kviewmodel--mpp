package ru.leroymerlin.mpp.kviewmodel.odyssey

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember
import ru.leroymerlin.mpp.kviewmodel.KViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
public inline fun <reified T : KViewModel> StoredViewModel(
    noinline factory: @DisallowComposableCalls () -> T,
    noinline content: @Composable (T) -> Unit
) {
    val currentScreen = LocalRootController.current.currentScreen
    val screenKey = currentScreen.value.screen.key
    val viewModel = remember { ViewModelStore.getOrPut(screenKey, factory) }
    content(viewModel)
}