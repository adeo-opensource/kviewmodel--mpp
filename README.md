# KViewModel

KViewModel it's a lightweight library for MVVM or MVI pattern. Works with Jetpack Compose, XML, UIKit.

Works with Kotlin Multiplatform and Compose Multiplatform!

### Implementation

Kotlin DSL

```kotlin
implementation("com.adeo:kviewmodel:0.6") // Core functions
implementation("com.adeo:kviewmodel-compose:0.6") // Compose extensions
implementation("com.adeo:kviewmodel-odyssey:0.6") // Odyssey extensions
```

```groovy
implementation "com.adeo:kviewmodel:0.6" // Core functions
implementation "com.adeo:kviewmodel-compose:0.6" // Compose extensions
implementation "com.adeo:kviewmodel-odyssey:0.6" // Odyssey extensions
```

### How to use

#### Common Code

```kotlin
class TestViewModel : BaseSharedViewModel<TestViewState, TestAction, TestEvent>(initialState = TestViewState())
```

Events - for user interaction

```kotlin
sealed class TestEvent {
    object IncrementClick : TestEvent()
    object DecrementClick : TestEvent()
    object DetailClick : TestEvent()
}
```

Actions - single action from view model like show snackbar or navigation

```kotlin
sealed class TestAction {
    data class OpenDetail(val param: Int) : TestAction()
}
```

ViewState - your current screen state (fields, loaders, etc)

```kotlin
data class TestViewState(
    val titleText: String = "",
    val counter: Int = 0
)
```

#### Compose Multiplatform

```kotlin
ViewModel(factory = { TestViewModel() }) { viewModel ->
    val viewState = viewModel.viewStates().observeAsState()
    val viewAction = viewModel.viewActions().observeAsState()

    Text(text = viewState.titleText)
    
    viewAction.value?.let { action -> 
        when (action) {
            is TestAction.FirstCase -> TODO()
            is TestAction.SecondCase -> TODO()
        }
    }
}
```

#### [Odyssey](https://github.com/AlexGladkov/Odyssey) integration
Allows you to save the ViewModel
```kotlin
setupNavigation(
    startScreen = "YourStartScreen",
    providers = yourCustomProviders
) {
    LocalRootController.current.setupWithViewModels()
    generateGraph()
}
```

```kotlin
StoredViewModel(factory = { TestViewModel() }) { viewModel ->
    // usual code like above
}
```

#### iOS

```swift
// ViewState
testViewModel.viewStates().watch { [weak self] viewState in
    guard let self = self else { return }
    
    self.titleView.text = viewState.someText 
}

// Action
testViewModel.viewActions().watch { [weak self] viewAction in
    guard let self = self, let viewAction = viewAction else { return }
    
    switch viewAction {
        case let args as TestAction.OpenDetail:
            self.presentDetail(param: args.param)
                    
        default: break 
    }
}
```

### Problems

Feel free to make issues, we will try to fix it as fast as we can! For proposals, you can also use issue section 
