package com.example.discovermars.image.imagedetail

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.discovermars.common.startWithFade
import com.example.discovermars.databinding.FragmentImageDetailBinding
import com.example.discovermars.dependencyInjection.ViewModelFactory
import com.example.discovermars.image.ImageDetailViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ImageDetailFragment : DaggerFragment() {

    private lateinit var viewModel: ImageDetailViewModel
    private lateinit var binding: FragmentImageDetailBinding

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

        binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ImageDetailViewModel::class.java)
        viewModel.handleEvent(ImageDetailEvent.OnStart(imageId))
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        observeViewModel()

        (binding.imvMarsBackground2.drawable as AnimationDrawable).startWithFade()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.getState().observe(
            viewLifecycleOwner,
            Observer { t ->
                if (t != null) {
                    binding.imageDetailView.load(t.image?.imageUrl?.replace("http:", "https:"))
                    binding.lblDetailRover.text = t.image?.contents
                    binding.lblDetailMessageDate.text = t.image?.creationDate
                    binding.lblDetailSol.text = "Sol: " + t.image?.sol.toString()
                    binding.lblDetailCameraName.text = "Camera: " + t.image?.camera
                    binding.lblDetailRoverStatus.text = "Rover Status: " + t.image?.rover?.status
                }
            }
        )
    }

}