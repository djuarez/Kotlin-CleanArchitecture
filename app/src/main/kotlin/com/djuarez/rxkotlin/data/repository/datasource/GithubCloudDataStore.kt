package com.djuarez.rxkotlin.data.repository.datasource

import com.djuarez.rxkotlin.data.cache.DBHelper
import com.djuarez.rxkotlin.data.entity.GithubEntity
import com.djuarez.rxkotlin.data.net.RestApi
import rx.Observable

class GithubCloudDataStore(private val restApi: RestApi, private val dbHelper: DBHelper) : GithubDataStore {

    override fun github(id: String): Observable<GithubEntity> {
        return restApi.githubUser(id).doOnNext {
            dbHelper.save(it)
        }

    }

    override fun githubList(page: Int?, perPage: Int?): Observable<List<GithubEntity>> {
        throw UnsupportedOperationException()
    }

}