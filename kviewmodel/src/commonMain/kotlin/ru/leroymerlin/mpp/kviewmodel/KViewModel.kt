package ru.leroymerlin.mpp.kviewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

public abstract class KViewModel {

    private val coroutineTags = hashMapOf<String, CoroutineScope>()

    public val viewModelScope: CoroutineScope
        get() = coroutineTags[MAIN_JOB_KEY] ?: launchNewScope()

    protected open fun onCleared() {

    }

    public fun clear() {
        coroutineTags.forEach { it.value.cancel() }
        onCleared()
    }

    // Launch view model scope except you provide a new key
    public fun launchNewScope(key: String = MAIN_JOB_KEY): CoroutineScope =
        coroutineTags.getOrPut(key) {
            CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        }

    public companion object {
        private const val MAIN_JOB_KEY = "main.viewmodel.shared.coroutine.job"
    }

}