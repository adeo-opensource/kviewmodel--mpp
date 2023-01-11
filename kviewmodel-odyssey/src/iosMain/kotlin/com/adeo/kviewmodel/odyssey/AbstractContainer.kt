package com.adeo.kviewmodel.odyssey

import com.adeo.kviewmodel.KViewModel
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

public actual class AbstractContainer : SynchronizedObject() {

    private val container = HashMap<String, KViewModel>()

    public actual fun remove(screenKey: String) {
        synchronized(this) {
            container.forEach {
                if (it.key.startsWith(screenKey)) {
                    it.value.clear()
                    container -= it.key
                }
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