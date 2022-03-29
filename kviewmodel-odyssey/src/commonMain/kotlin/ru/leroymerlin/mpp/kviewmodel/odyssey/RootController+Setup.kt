package ru.leroymerlin.mpp.kviewmodel.odyssey

import ru.alexgladkov.odyssey.compose.RootController

public fun RootController.setupWithViewModels() {
    onScreenRemove = {
        ViewModelStore.remove(it.key)
    }
}