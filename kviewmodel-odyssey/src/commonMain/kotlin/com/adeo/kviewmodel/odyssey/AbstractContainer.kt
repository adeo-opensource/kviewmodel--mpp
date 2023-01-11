package com.adeo.kviewmodel.odyssey

import com.adeo.kviewmodel.KViewModel

public expect class AbstractContainer() {
    public fun remove(screenKey: String)
    public fun getOrPut(key: String, factory: () -> KViewModel): KViewModel
}