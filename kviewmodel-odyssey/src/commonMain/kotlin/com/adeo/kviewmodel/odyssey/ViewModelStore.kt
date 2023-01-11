package com.adeo.kviewmodel.odyssey

import androidx.compose.runtime.DisallowComposableCalls
import com.adeo.kviewmodel.KViewModel

public object ViewModelStore {

    @PublishedApi
    internal val abstractContainer: AbstractContainer = AbstractContainer()

    @PublishedApi
    internal inline fun <reified T : KViewModel> getOrPut(
        screenKey: String,
        noinline factory: @DisallowComposableCalls () -> T
    ): T {
        val key = "${screenKey}_${T::class.simpleName}"
        return abstractContainer.getOrPut(key, factory) as T
    }

    public fun remove(screenKey: String) {
        abstractContainer.remove(screenKey)
    }

}