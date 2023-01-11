package com.adeo.kviewmodel.odyssey

import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

actual class ConcurrentHashMap<K, V>(
    private val delegate: HashMap<K, V> = HashMap()
) : MutableMap<K, V> by delegate {

    actual constructor() : this(HashMap())

    private val sync = SynchronizedObject()

    override val size: Int
        get() = synchronized(sync) { delegate.size }

    override fun get(key: K): V? = synchronized(sync) { delegate.get(key) }

    override fun put(key: K, value: V): V? = synchronized(sync) { delegate.put(key, value) }

    override fun remove(key: K): V? = synchronized(sync) { delegate.remove(key) }
}
