package com.adeo.kviewmodel.odyssey

public actual class ConcurrentHashMap<K, V>(
    private val delegate: HashMap<K, V> = HashMap()
) : MutableMap<K, V> by delegate {

    public actual constructor() : this(HashMap())

}
