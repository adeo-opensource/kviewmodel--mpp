package detail

import detail.models.DetailAction
import detail.models.DetailEvent
import detail.models.DetailViewState
import ru.leroymerlin.mpp.kviewmodel.BaseSharedViewModel

class DetailViewModel: BaseSharedViewModel<DetailViewState, DetailAction, DetailEvent>() {

    init {
        viewState = DetailViewState()
    }

    override fun obtainEvent(viewEvent: DetailEvent) {
        when (viewEvent) {
            DetailEvent.Launch -> showText()
        }
    }

    private fun showText() {
        viewState = DetailViewState(testData = "Hello, Launch")
    }
}