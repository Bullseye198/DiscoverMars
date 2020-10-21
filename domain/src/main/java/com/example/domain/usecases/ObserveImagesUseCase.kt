package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.FlowUseCase
import com.example.domain.image.IImageRepository
import com.example.domain.image.model.Image
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ObserveImagesUseCase @Inject constructor(
    private val iImageRepository: IImageRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : FlowUseCase<List<Image>, ObserveImagesUseCase.Params>(appCoroutineDispatchers) {

    private val selectedCameraStream: MutableStateFlow<String> = MutableStateFlow("")

    override fun buildStream(params: Params?): Flow<List<Image>> {
        return iImageRepository.observeImages(params!!.earthDate, params.rover)
            .combine(selectedCameraStream) { images, selectedCamera ->
                images.filter {
                    it.camera?.name == selectedCamera
                }
            }
    }

    fun onSelectedCameraChanged(newCamera: String) {
        selectedCameraStream.value = newCamera
    }

    data class Params(
        val rover: String,
        val earthDate: String
    )
}