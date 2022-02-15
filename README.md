#KViewModel

KViewModel it's a lightweight library for MVVM or MVI pattern. 
Works with Jetpack Compose, XML, UIKit. 

Works with Kotlin Multiplatform and Compose Multiplatform!

### Implementation

Kotlin DSL
`implementation("ru.leroymerlin.mpp:kviewmodel:0.2") // Core functions`

`implementation("ru.leroymerlin.mpp:kviewmodel-compose:0.2") // Compose extensions`

Groovy
`implementation "ru.leroymerlin.mpp:kviewmodel:0.2" // Core functions`

`implementation "ru.leroymerlin.mpp:kviewmodel-compose:0.2" // Compose extensions`

### How to use

#### Common Code
```kotlin
class TestViewModel: BaseSharedViewModel<TestViewState, TestAction, TestEvent>()
```

Events - for user interaction
```kotlin
sealed class TestEvent {
    object Launch : TestEvent()
    object OpenDetail : TestEvent()
    data class EventWithParam(val value: Int) : TestEvent()
}
```

Actions - single action from view model like show snackbar or navigation
```kotlin
sealed class TestAction {
    object OpenDetail : TestAction()
}
```

ViewState - your current screen state (fields, loaders, etc)
```kotlin
data class TestViewState(
    val someValue: Int = 0,
    val someText: String = ""
)
```

#### Compose Multiplatform
```kotlin
ViewModel(factory = { TestViewModel() }) { viewModel ->
    val viewState = viewModel.viewStates().observeAsState()
    val viewAction = viewModel.viewActions().observeAsState()
    
    viewState.value?.let { state -> }
    viewAction.value?.let { action -> }
}
```

#### iOS 
```
// ViewState
testViewModel.viewStates().watch { [weak self] viewState in
    guard let self = self, let viewState = viewState else { return }
            
    self.titleView.text = viewState.someText 
}

// Action
testViewModel.viewActions().watch { [weak self] viewAction in
    guard let self = self, let viewAction = viewAction else { return }
            
    switch viewAction {
    case _ as TestAction.OpenDetail:
        self.presentDetail()
                
    default:
       break 
    }
}
```

For any question feel free to ask

[Alex Gladkov](aleksey.gladkov@leroymerlin.ru)

[Vyacheslav Kornienko](vyacheslav.kornienko@leroymerlin.ru)

[Danil Yudov](Danil.Iudov@leroymerlin.ru)