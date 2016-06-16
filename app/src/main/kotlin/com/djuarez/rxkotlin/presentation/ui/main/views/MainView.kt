package com.djuarez.rxkotlin.presentation.ui.main.views

import com.djuarez.rxkotlin.domain.model.Github


interface MainView {
    fun renderView(github: Github)
    fun showError(throwable: Throwable)
}