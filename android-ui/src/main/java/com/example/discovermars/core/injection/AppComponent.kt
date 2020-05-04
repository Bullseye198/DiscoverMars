package com.example.discovermars.core.injection

import android.content.Context
import com.example.discovermars.MyApplication
import com.example.discovermars.core.injection.module.ApplicationModule
import com.example.discovermars.core.injection.module.ApplicationModuleBinds
import com.example.discovermars.core.injection.module.DaoModule
import com.example.remote.photo.RemoteModule
import com.example.discovermars.dependencyInjection.AppDataModule
import com.example.discovermars.dependencyInjection.ImageUIModule
import com.example.discovermars.dependencyInjection.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
    ApplicationModuleBinds::class,
    ViewModelModule::class,
    AndroidInjectionModule::class,
    ApplicationModule::class,
    ImageUIModule::class,
    DaoModule::class,
    ApplicationModule::class,
    AppDataModule::class,
    RemoteModule::class]
)


interface AppComponent : AndroidInjector<MyApplication>{
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}