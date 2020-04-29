package com.example.discovermars.core.injection.module

import com.example.discovermars.dependencyInjection.ImageUIModule
import com.example.discovermars.features.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ApplicationModuleBinds {

    @ContributesAndroidInjector(
        modules = [ImageUIModule::class]
    )
    fun contributeMainActivity(): MainActivity
}