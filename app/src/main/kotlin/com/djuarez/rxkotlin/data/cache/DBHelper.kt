package com.djuarez.rxkotlin.data.cache

import com.djuarez.rxkotlin.data.entity.GithubEntity
import io.realm.Realm
import io.realm.RealmQuery
import rx.Observable
import javax.inject.Inject

/**
 * Created by djuarez on 16/6/16.
 */

class DBHelper @Inject constructor() {

    private val realm: Realm = Realm.getDefaultInstance()

    fun save(github: GithubEntity) {
      /*  realm.beginTransaction()
        realm.copyToRealmOrUpdate(github)
        realm.commitTransaction()
        */
    }

    fun get(id: String): Observable<GithubEntity> {
        return Observable.create { subscriber ->
            val savedUser: GithubEntity? = RealmQuery.createQuery(realm,
                    GithubEntity::class.java).equalTo("name", id)
                    .findFirst()
            if (savedUser != null) {
                subscriber.onNext(savedUser)
                subscriber.onCompleted()
            } else subscriber.onError(Throwable())

        }
    }

}
