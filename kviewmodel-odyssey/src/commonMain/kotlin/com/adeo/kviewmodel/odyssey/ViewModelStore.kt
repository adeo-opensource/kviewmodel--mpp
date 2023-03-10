package com.adeo.kviewmodel.odyssey

import androidx.compose.runtime.DisallowComposableCalls
import com.adeo.kviewmodel.KViewModel

public object ViewModelStore {

    @PublishedApi
    internal val viewModelStore: ConcurrentHashMap<String, KViewModel> = ConcurrentHashMap()

    @PublishedApi
    internal inline fun <reified T : KViewModel> getOrPut(
        screenKey: String,
        factory: @DisallowComposableCalls () -> T
    ): T {
        val key = "${screenKey}_${T::class.name}"
        return viewModelStore.getOrPut(key, factory) as T
    }

    public fun remove(screenKey: String) {
        viewModelStore.forEach {
            if (it.key.startsWith(screenKey)) {
                it.value.clear()
                viewModelStore -= it.key
            }
        }
    }

}