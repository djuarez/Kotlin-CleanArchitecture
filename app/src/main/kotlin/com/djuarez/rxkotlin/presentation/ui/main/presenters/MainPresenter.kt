package com.djuarez.rxkotlin.presentation.ui.main.presenters

import com.djuarez.rxkotlin.domain.usingcases.GithubUseCase
import com.djuarez.rxkotlin.presentation.internal.di.scope.PerActivity
import com.djuarez.rxkotlin.presentation.ui.main.views.MainView
import rx.functions.Action1
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject
constructor(private val githubUseCase: GithubUseCase) {

    var view: MainView? = null

    fun onCreate() {
        githubUseCase.setId("djuarez")
        githubUseCase.execute(Action1 { view?.renderView(it) }, Action1 { view?.showError(it) })
    }
}

