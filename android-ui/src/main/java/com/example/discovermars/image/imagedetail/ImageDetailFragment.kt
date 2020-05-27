package com.example.discovermars.image.imagedetail

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.discovermars.R
import com.example.discovermars.common.startWithFade
import com.example.discovermars.dependencyInjection.ViewModelFactory
import com.example.discovermars.image.ImageDetailViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_image_detail.*
import javax.inject.Inject

class ImageDetailFragment : DaggerFragment() {

    private lateinit var viewModel: ImageDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var imageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageId = ImageDetailFragmentArgs.fromBundle(requireArguments()).imageId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, viewModelFactory).get(ImageDetailViewModel::class.java)
        viewModel.handleEvent(ImageDetailEvent.OnStart(imageId))
        return inflater.inflate(R.layout.fragment_image_detail, container, false)
    }

    override fun onStart() {
        super.onStart()

        observeViewModel()

        (imv_mars_background2.drawable as AnimationDrawable).startWithFade()
    }

    private fun observeViewModel() {
        viewModel.getState().observe(
            viewLifecycleOwner,
            Observer { t ->
                if (t != null) {
                    imageDetailView.load(t.image?.imageUrl?.replace("http:", "https:"))
                }
            }
        )
    }

}