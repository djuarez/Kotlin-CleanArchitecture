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

import android.app.Application
import android.content.Context
import com.djuarez.rxkotlin.presentation.internal.di.components.ApplicationComponent
import com.djuarez.rxkotlin.presentation.internal.di.components.DaggerApplicationComponent
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Android Main Application
 */
class AndroidApplication : Application() {

    val component: ApplicationComponent
        get() = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    override fun onCreate() {
        super.onCreate()
        initRealm()
        component.inject(this)
    }

    private fun initRealm() {
        val realmConfiguration: RealmConfiguration = RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    companion object {

        operator fun get(context: Context): AndroidApplication {
            return context.applicationContext as AndroidApplication
        }
    }
}
