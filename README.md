# KViewModel

KViewModel it's a lightweight library for MVVM or MVI pattern. Works with Jetpack Compose, XML, UIKit.

Works with Kotlin Multiplatform and Compose Multiplatform!

### Implementation

Kotlin DSL

```kotlin
implementation("com.adeo:kviewmodel:0.5") // Core functions
implementation("com.adeo:kviewmodel-compose:0.5") // Compose extensions
```

```groovy
implementation "com.adeo:kviewmodel:0.5" // Core functions
implementation "com.adeo:kviewmodel-compose:0.5" // Compose extensions
```

### How to use

#### Common Code

```kotlin
class TestViewModel : BaseSharedViewModel<TestViewState, TestAction, TestEvent>()
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

### Problems

Feel free to make issues, we will try to fix it as fast as we can! For proposals, you can also use issue section 
