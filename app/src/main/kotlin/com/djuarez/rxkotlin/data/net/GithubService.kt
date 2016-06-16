package com.djuarez.rxkotlin.data.net

import com.djuarez.rxkotlin.data.entity.GithubEntity
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by djuarez on 17/12/15.
 */

interface GithubService {
    @GET("/users/{username}")
    fun getGithubUser(@Path("username") username: String): Observable<GithubEntity>
}