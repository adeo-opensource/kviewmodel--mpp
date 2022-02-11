package adeo.kviewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.jvm.Volatile

abstract class KViewModel {

    private val coroutineTags = hashMapOf<String, CoroutineScope>()
    private val mainJobKey = "main.viewmodel.shared.coroutine.job"

    @Volatile
    private var isCleared = false

    val viewModelScope: CoroutineScope
        get() {
            val scope: CoroutineScope? = coroutineTags[mainJobKey]
            if (scope != null) {
                return scope
            }
            return launchNewScope()
        }

    protected open fun onCleared() {}

    fun clear() {
        isCleared = true
        // Since clear() is final, this method is still called on mock objects
        // and in those cases, mBagOfTags is null. It'll always be empty though
        // because setTagIfAbsent and getTag are not final so we can skip
        // clearing it
        for (value in coroutineTags.values) {
            value.cancel()
        }

        onCleared()
    }

    // Launch view model scope except you provide a new key
    fun launchNewScope(key: String = mainJobKey): CoroutineScope =
        if (coroutineTags.containsKey(key)) {
            coroutineTags[key]!!
        } else {
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
            coroutineTags[key] = scope
            scope
        }
}