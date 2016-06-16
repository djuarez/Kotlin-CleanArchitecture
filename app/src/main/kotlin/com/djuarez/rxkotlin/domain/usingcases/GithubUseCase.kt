package com.djuarez.rxkotlin.domain.usingcases

import com.djuarez.rxkotlin.domain.executor.PostExecutionThread
import com.djuarez.rxkotlin.domain.executor.ThreadExecutor
import com.djuarez.rxkotlin.domain.model.Github
import com.djuarez.rxkotlin.domain.repository.GithubRepository
import rx.Observable
import javax.inject.Inject

class GithubUseCase @Inject constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread,
                                        private val githubRepository: GithubRepository) : UseCase<Github>(threadExecutor, postExecutionThread) {
    private var username: String = ""

    fun setId(username: String) {
        this.username = username
    }

    override fun buildUseCaseObservable(): Observable<Github> {
        return this.githubRepository.github(username)
    }
}