package com.example.discovermars.dependencyInjection

import com.example.discovermars.image.imagedetail.ImageDetailFragment
import com.example.discovermars.image.imagelist.ImageListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ImageUIModule {

    @ContributesAndroidInjector
    fun contributesImageListFragment(): ImageListFragment

    @ContributesAndroidInjector
    fun contributesImageDetailFragment(): ImageDetailFragment
}