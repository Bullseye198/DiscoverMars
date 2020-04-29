package com.example.discovermars.image.imagelist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.discovermars.image.ImageListViewModel
import com.example.discovermars.R
import com.example.discovermars.dependencyInjection.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ImageListFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            ImageListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ImageListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ImageListViewModel::class.java)
        // Use viewModel

    }

}