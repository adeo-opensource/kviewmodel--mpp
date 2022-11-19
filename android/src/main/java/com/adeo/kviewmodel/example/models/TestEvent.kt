package com.adeo.kviewmodel.example.models

sealed class TestEvent {
    object IncrementClick : TestEvent()
    object DecrementClick : TestEvent()
    object DetailClick : TestEvent()
}