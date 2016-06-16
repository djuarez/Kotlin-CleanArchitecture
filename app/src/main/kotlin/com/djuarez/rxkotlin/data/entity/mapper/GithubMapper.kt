package com.djuarez.rxkotlin.data.entity.mapper

import com.djuarez.rxkotlin.data.entity.GithubEntity
import com.djuarez.rxkotlin.domain.model.Github
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class GithubMapper @Inject constructor() : EntryEntityMapper<Github, GithubEntity>() {

    override fun transform(entity: GithubEntity?): Github? {
        if (entity != null) {
            val github = Github()
            github.id = entity.id
            github.avatarUrl = entity.avatarUrl
            github.name = entity.name
            github.publicRepos = entity.publicRepos
            return github
        }
        return null
    }
}