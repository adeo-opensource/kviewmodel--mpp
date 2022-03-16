package com.adeo.kviewmodel.example.common.detail

import com.adeo.kviewmodel.BaseSharedViewModel
import com.adeo.kviewmodel.example.common.detail.models.DetailAction
import com.adeo.kviewmodel.example.common.detail.models.DetailEvent
import com.adeo.kviewmodel.example.common.detail.models.DetailViewState

class DetailViewModel(param: Int) : BaseSharedViewModel<DetailViewState, DetailAction, DetailEvent>(
    DetailViewState(text = "Got $param")
) {

    override fun obtainEvent(viewEvent: DetailEvent) {

    }

}