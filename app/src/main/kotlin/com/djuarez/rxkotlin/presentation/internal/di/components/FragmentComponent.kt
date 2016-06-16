package com.djuarez.rxkotlin.presentation.internal.di.components

import com.djuarez.rxkotlin.presentation.internal.di.modules.FragmentModule
import com.djuarez.rxkotlin.presentation.internal.di.scope.PerFragment
import dagger.Component

@PerFragment
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent
