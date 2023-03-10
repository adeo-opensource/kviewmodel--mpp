package com.adeo.kviewmodel.odyssey

import kotlin.reflect.KClass

public actual val KClass<*>.name: String get() = qualifiedName!!