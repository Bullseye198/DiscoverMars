package com.example.discovermars.dependencyInjection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.discovermars.features.main.MainActivity
import com.example.discovermars.image.ImageDetailViewModel
import com.example.discovermars.image.ImageListViewModel
import com.example.discovermars.userInterface.MainActivityViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass


@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey (MainActivityViewModel::class)
    internal abstract fun mainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey (ImageDetailViewModel::class)
    internal abstract fun imageDetailViewModel(viewModel: ImageDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey (ImageListViewModel::class)
    internal abstract fun imageListViewModel(viewModel: ImageListViewModel): ViewModel

}


