package com.example.discovermars.image.imagelist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.discovermars.image.ImageListViewModel
import com.example.discovermars.R
import com.example.discovermars.dependencyInjection.ViewModelFactory
import com.example.domain.image.model.Image
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_image_list.*
import kotlinx.android.synthetic.main.item_image.*
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
        setUpAdapter()

    }


    private fun setUpAdapter() {
        adapter = ImageListAdapter()
        rec_list_fragment.adapter = adapter

        adapter.event.observe(
            viewLifecycleOwner,
            Observer {
                if(it is ImageListEvent.OnImageItemClick){
                val direction = ImageListFragmentDirections.actionImageListFragmentToImageDetail()
                findNavController().navigate(direction)
            }
            }
        )

        viewModel.imageList.observe(   viewLifecycleOwner,
            Observer {
                adapter.submitList(it)
            })
    }


}