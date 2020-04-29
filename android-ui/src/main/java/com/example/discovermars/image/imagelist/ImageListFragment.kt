package com.example.discovermars.image.imagelist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.discovermars.image.ImageListViewModel
import com.example.discovermars.R
import com.example.discovermars.dependencyInjection.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_image_list.*
import javax.inject.Inject

class ImageListFragment : DaggerFragment() {

    private lateinit var viewModel: ImageListViewModel
    private lateinit var adapter: ImageListAdapter


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ImageListViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        rec_list_fragment.adapter = null
    }

    override fun onStart() {
        super.onStart()



    }
/*
    private fun setUpAdapter() {
        adapter = ImageListAdapter()
        adapter.event.observe(
            viewLifecycleOwner,
            Observer{
                viewModel.handleEvent(it)
            }
        )
        rec_list_fragment.adapter = adapter
    }

 */


}