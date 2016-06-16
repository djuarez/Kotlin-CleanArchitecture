package com.djuarez.rxkotlin.presentation.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.djuarez.rxkotlin.R
import com.djuarez.rxkotlin.domain.model.Github
import com.djuarez.rxkotlin.presentation.AndroidApplication
import com.djuarez.rxkotlin.presentation.internal.di.components.ActivityComponent
import com.djuarez.rxkotlin.presentation.internal.di.components.DaggerActivityComponent
import com.djuarez.rxkotlin.presentation.internal.di.modules.ActivityModule
import com.djuarez.rxkotlin.presentation.ui.main.presenters.MainPresenter
import com.djuarez.rxkotlin.presentation.ui.main.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("pemma","daskdas,")
        component.inject(this)
        mainPresenter.view = this;
        mainPresenter.onCreate()
    }

    private val component: ActivityComponent
        get() = DaggerActivityComponent.builder()
                .applicationComponent((application as AndroidApplication).component)
                .activityModule(ActivityModule(this))
                .build()

    override fun renderView(github: Github) {
        Glide.with(this).load(github?.avatarUrl).into(userImage)
        userName.text = github?.name
        publicRepos.text = "Public Repos: " + github?.publicRepos
                .toString()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, "Something was wrong", Toast.LENGTH_LONG).show()
    }
}
