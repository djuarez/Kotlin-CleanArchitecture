package com.djuarez.rxkotlin.data.repository.datasource

import com.djuarez.rxkotlin.data.entity.GithubEntity
import rx.Observable

interface GithubDataStore {
    fun githubList(page: Int?, perPage: Int?): Observable<List<GithubEntity>>

    fun github(id: String): Observable<GithubEntity>
}
