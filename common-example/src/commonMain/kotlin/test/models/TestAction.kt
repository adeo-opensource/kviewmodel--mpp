package test.models

sealed class TestAction {
    data class OpenDetail(val param: Int) : TestAction()
}