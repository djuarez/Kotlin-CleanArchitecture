/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.djuarez.rxkotlin.domain.usingcases

import com.djuarez.rxkotlin.domain.executor.PostExecutionThread
import com.djuarez.rxkotlin.domain.executor.ThreadExecutor
import rx.Observable
import rx.Subscriber
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).

 * By convention each UseCase implementation will return the result using a [Subscriber]
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<T> protected constructor(private val threadExecutor: ThreadExecutor, private val postExecutionThread: PostExecutionThread) {

    private var subscription = Subscriptions.empty()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    protected abstract fun buildUseCaseObservable(): Observable<T>

    /**
     * Executes the current use case.

     * @param useCaseSubscriber The guy who will be listen to the observable build with [ ][.buildUseCaseObservable].
     */
    @SuppressWarnings("unchecked")
    fun execute(useCaseSubscriber: Subscriber<T>) {
        this.subscription = this.buildUseCaseObservable().subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecutionThread.scheduler).subscribe(useCaseSubscriber)
    }

    @SuppressWarnings("unchecked")
    fun execute(onNext: Action1<T>, onError: Action1<Throwable>) {
        this.subscription = this.buildUseCaseObservable().subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecutionThread.scheduler).subscribe(onNext, onError)
    }

    @SuppressWarnings("unchecked")
    fun execute(onNext: Action1<T>, onError: Action1<Throwable>, onCompleted: Action0) {
        this.subscription = this.buildUseCaseObservable().subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecutionThread.scheduler).subscribe(onNext, onError, onCompleted)
    }

    /**
     * Unsubscribes from current [Subscription].
     */
    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }
}