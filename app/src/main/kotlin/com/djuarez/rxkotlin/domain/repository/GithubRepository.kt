package com.djuarez.rxkotlin.domain.repository

import com.djuarez.rxkotlin.domain.model.Github
import rx.Observable

interface GithubRepository {

    fun github(id: String): Observable<Github>

    fun githubList(page: Int?, perPage: Int?): Observable<List<Github>>
}