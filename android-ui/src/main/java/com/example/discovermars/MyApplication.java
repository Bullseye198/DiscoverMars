package com.example.discovermars;

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector

class MyApplication: DaggerApplication(), HasAndroidInjector {


        override fun onCreate() {
        super.onCreate()

        }

        override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
        }
        }