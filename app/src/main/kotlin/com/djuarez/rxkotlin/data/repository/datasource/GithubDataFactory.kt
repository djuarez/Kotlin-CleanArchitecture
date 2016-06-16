package com.djuarez.rxkotlin.data.repository.datasource

import com.djuarez.rxkotlin.data.cache.DBHelper
import com.djuarez.rxkotlin.data.net.RestApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubDataFactory @Inject constructor(private val restApi: RestApi, val dbHelper: DBHelper) {

    fun createCloudDataStore(): GithubDataStore {
        return GithubCloudDataStore(restApi, dbHelper)
    }

    fun createDBDataStore(): GithubDataStore {
        return GithubDBDataStore(dbHelper)
    }

}

