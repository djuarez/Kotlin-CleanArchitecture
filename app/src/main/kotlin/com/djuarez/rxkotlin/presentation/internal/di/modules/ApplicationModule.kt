/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.djuarez.rxkotlin.presentation

import android.content.Context
import android.content.SharedPreferences
import com.djuarez.rxkotlin.data.executor.JobExecutor
import com.djuarez.rxkotlin.data.repository.GithubDataRepository
import com.djuarez.rxkotlin.domain.executor.PostExecutionThread
import com.djuarez.rxkotlin.domain.executor.ThreadExecutor
import com.djuarez.rxkotlin.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule(private val androidApplication: AndroidApplication) {

    @Provides
    @Singleton
    fun application(): AndroidApplication {
        return androidApplication
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return androidApplication
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return androidApplication.getSharedPreferences("app", Context.MODE_APPEND)
    }

    @Provides
    @Singleton
    fun provideGithubRepository(dataRepository: GithubDataRepository): GithubRepository {
        return dataRepository

    }
}
