package com.djuarez.rxkotlin.presentation.internal.di.modules

import android.support.v4.app.Fragment
import com.djuarez.rxkotlin.presentation.internal.di.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val baseFragment: Fragment) {

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerFragment
    fun provideFragment(): Fragment {
        return this.baseFragment
    }
}