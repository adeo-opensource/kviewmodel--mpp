package test.models

sealed class TestEvent {
    data class DetailClick(val param: Int) : TestEvent()
    data class EventWithParam(val value: Int) : TestEvent()
}