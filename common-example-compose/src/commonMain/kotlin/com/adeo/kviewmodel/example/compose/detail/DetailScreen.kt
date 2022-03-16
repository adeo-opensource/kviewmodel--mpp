package com.adeo.kviewmodel.example.compose.detail

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.ViewModel
import com.adeo.kviewmodel.example.common.detail.DetailViewModel

@Composable
fun DetailScreen(param: Int) {
    ViewModel(factory = { DetailViewModel(param) }) { _ ->

    }
}