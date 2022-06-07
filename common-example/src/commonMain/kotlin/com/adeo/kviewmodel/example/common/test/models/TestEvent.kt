package com.adeo.kviewmodel.example.common.test.models

sealed class TestEvent {
    object IncrementClick : TestEvent()
    object DecrementClick : TestEvent()
    object DetailClick : TestEvent()
    object ActionInvoked : TestEvent()
}