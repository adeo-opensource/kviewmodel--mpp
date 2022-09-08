package com.adeo.kviewmodel

import kotlinx.coroutines.flow.*

public class WrappedStateFlow<T : Any>(private val origin: StateFlow<T>) : StateFlow<T> by origin

public class WrappedSharedFlow<T : Any?>(private val origin: SharedFlow<T>) :
    SharedFlow<T> by origin
