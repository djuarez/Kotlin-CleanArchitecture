package com.djuarez.rxkotlin.presentation.internal.di.scope

import java.lang.annotation.Retention

import javax.inject.Scope

import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by raulcobos on 10/3/16.
 */
@Scope
@Retention(RUNTIME)
annotation class PerFragment
