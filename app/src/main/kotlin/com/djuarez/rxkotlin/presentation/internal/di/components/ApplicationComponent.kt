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
package com.djuarez.rxkotlin.presentation.internal.di.components

import android.content.Context
import com.djuarez.rxkotlin.domain.executor.PostExecutionThread
import com.djuarez.rxkotlin.domain.executor.ThreadExecutor
import com.djuarez.rxkotlin.domain.repository.GithubRepository
import com.djuarez.rxkotlin.presentation.AndroidApplication
import com.djuarez.rxkotlin.presentation.ApplicationModule
import com.djuarez.rxkotlin.presentation.navigation.Navigator
import dagger.Component
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(androidApplication: AndroidApplication)

    val androidApplication: AndroidApplication

    fun context(): Context

    fun navigator(): Navigator

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun githubRepository(): GithubRepository
}
