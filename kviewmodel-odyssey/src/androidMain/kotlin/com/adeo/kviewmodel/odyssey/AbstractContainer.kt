package com.adeo.kviewmodel.odyssey

import com.adeo.kviewmodel.KViewModel
import java.util.concurrent.ConcurrentHashMap

public actual class AbstractContainer {

    private val container = ConcurrentHashMap<String, KViewModel>()

    public actual fun remove(screenKey: String) {
        container.forEach {
            if (it.key.startsWith(screenKey)) {
                it.value.clear()
                container -= it.key
            }
        }
    }

    public actual fun getOrPut(
        key: String,
        factory: () -> KViewModel
    ): KViewModel {
        return container.getOrPut(key, factory)
    }

}