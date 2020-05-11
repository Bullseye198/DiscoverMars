package com.cm.baseAndroid.injection

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactoy(factory: ViewModelFactory): ViewModelProvider.Factory
}
