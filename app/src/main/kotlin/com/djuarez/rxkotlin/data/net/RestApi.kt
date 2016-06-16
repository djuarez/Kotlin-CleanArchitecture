package com.djuarez.rxkotlin.data.net

/**
 * Created by djuarez on 17/12/15.
 */

import com.djuarez.rxkotlin.data.entity.GithubEntity
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [RestApi] implementation for retrieving data from the network.
 */
@Singleton
class RestApi @Inject constructor() {

    private val service: GithubService

    init {
        val builder = OkHttpClient.Builder().addInterceptor(getLogginInterceptor())
        val retro = Retrofit.Builder().baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build()).build()
        service = retro.create(GithubService::class.java)
    }

    fun githubUser(id: String): Observable<GithubEntity> {
        return service.getGithubUser(id);
    }

    fun getLogginInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

}