package com.adeo.kviewmodel.example.models

sealed class TestAction {
    data class OpenDetail(val param: Int) : TestAction()
}