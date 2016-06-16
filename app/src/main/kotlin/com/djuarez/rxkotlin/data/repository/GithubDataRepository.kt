package com.djuarez.rxkotlin.data.repository

import com.djuarez.rxkotlin.data.entity.mapper.GithubMapper
import com.djuarez.rxkotlin.data.repository.datasource.GithubDataFactory
import com.djuarez.rxkotlin.domain.model.Github
import com.djuarez.rxkotlin.domain.repository.GithubRepository
import rx.Observable
import javax.inject.Inject

class GithubDataRepository
@Inject constructor(private val mapper: GithubMapper, private val dataFactory: GithubDataFactory) : GithubRepository {

    override fun githubList(page: Int?, perPage: Int?): Observable<List<Github>> {
        return dataFactory.createCloudDataStore()
                .githubList(page, perPage)
                .map({ mapper.transform(it) })

    }

    override fun github(id: String): Observable<Github> {
        return dataFactory.createCloudDataStore().github(id).map({ mapper.transform(it) })
    }
}

