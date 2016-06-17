package com.djuarez.rxkotlin.presentation.ui.main.presenters

import com.djuarez.rxkotlin.domain.model.Github
import com.djuarez.rxkotlin.domain.usingcases.GithubUseCase
import com.djuarez.rxkotlin.presentation.internal.di.scope.PerActivity
import com.djuarez.rxkotlin.presentation.ui.main.views.MainView
import rx.lang.kotlin.FunctionSubscriber
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject
constructor(private val githubUseCase: GithubUseCase) {

    var view: MainView? = null

    fun onCreate() {
        githubUseCase.setId("djuarez")
        githubUseCase.execute(FunctionSubscriber<Github>()
                     .onNext { view?.renderView(it) }
                     .onError { view?.showError(it) })
    }
}

