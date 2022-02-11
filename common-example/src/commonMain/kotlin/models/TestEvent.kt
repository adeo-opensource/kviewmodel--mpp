package models

sealed class TestEvent {
    object Launch : TestEvent()
    data class EventWithParam(val value: Int) : TestEvent()
}