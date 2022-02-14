package test.models

sealed class TestEvent {
    object Launch : TestEvent()
    object OpenDetail : TestEvent()
    data class EventWithParam(val value: Int) : TestEvent()
}