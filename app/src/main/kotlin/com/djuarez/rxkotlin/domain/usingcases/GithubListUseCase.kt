package com.djuarez.rxkotlin.domain.usingcases

import com.djuarez.rxkotlin.domain.executor.PostExecutionThread
import com.djuarez.rxkotlin.domain.executor.ThreadExecutor
import com.djuarez.rxkotlin.domain.model.Github
import com.djuarez.rxkotlin.domain.repository.GithubRepository
import rx.Observable
import javax.inject.Inject

class GithubListUseCase @Inject
    constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread,
            private val githubRepository: GithubRepository) : UseCase<List<Github>>(threadExecutor, postExecutionThread) {
    private var page: Int? = null
    private var perPage: Int? = null

    fun setPagination(page: Int, perPage: Int) {
        this.page = page
        this.perPage = perPage
    }

    override fun buildUseCaseObservable(): Observable<List<Github>> {
        return this.githubRepository.githubList(page, perPage)
    }
}