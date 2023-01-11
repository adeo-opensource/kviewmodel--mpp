package com.adeo.kviewmodel.odyssey

import androidx.compose.runtime.DisallowComposableCalls
import com.adeo.kviewmodel.KViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized

@OptIn(InternalCoroutinesApi::class)
public object ViewModelStore : SynchronizedObject() {

    @PublishedApi
    internal val viewModelStore = HashMap<String, KViewModel>()

    @PublishedApi
    internal inline fun <reified T : KViewModel> getOrPut(
        screenKey: String,
        noinline factory: @DisallowComposableCalls () -> T
    ): T {
        val key = "${screenKey}_${T::class.simpleName}"
        return viewModelStore.getOrPut(key, factory) as T
    }

    public fun remove(screenKey: String) {
        synchronized(this) {
            viewModelStore.forEach {
                if (it.key.startsWith(screenKey)) {
                    it.value.clear()
                    viewModelStore -= it.key
                }
            }
        }
    }

}