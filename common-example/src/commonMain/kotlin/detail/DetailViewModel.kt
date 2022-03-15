package detail

import detail.models.DetailAction
import detail.models.DetailEvent
import detail.models.DetailViewState
import ru.leroymerlin.mpp.kviewmodel.BaseSharedViewModel

class DetailViewModel(param: Int) : BaseSharedViewModel<DetailViewState, DetailAction, DetailEvent>(
    DetailViewState(text = "Got $param")
) {

    override fun obtainEvent(viewEvent: DetailEvent) {

    }

}