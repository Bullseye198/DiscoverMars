package com.example.discovermars.image.imagelist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
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
    private lateinit var spinnerAdapter: ArrayAdapter<String>


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpImageListAdapter()
        setupSpinnerAdapter()
        observeViewModel()
        onDateSelected()
    }

    private fun setupSpinnerAdapter() {
        val cameras = resources.getStringArray(R.array.cameras).toList()

        val spinner = requireView().findViewById<Spinner>(R.id.spinner1)
        if (spinner != null) {
            spinnerAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, cameras
            )
            spinner.adapter = spinnerAdapter


            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.onNewCameraSelected(spinnerAdapter.getItem(position)!!)
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {
                }
            }
        }
    }

    private fun setNewCameras(cameras: List<String>) {
        spinnerAdapter.clear()
        spinnerAdapter.addAll(cameras)
    }

    private fun onDateSelected() {
        txtDate.setOnEditorActionListener { view, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                viewModel.onDateSelected(earthDate = txtDate.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        rec_list_fragment.adapter = null
        spinner1.adapter = null
    }

    private fun setUpImageListAdapter() {
        adapter = ImageListAdapter()
        rec_list_fragment.adapter = adapter
        adapter.event.observe(
            viewLifecycleOwner,
            Observer {
                if (it is ImageListEvent.OnImageItemClick) {
                    val direction =
                        ImageListFragmentDirections.actionImageListFragmentToImageDetail(it.imageId)
                    findNavController().navigate(direction)
                }
            }
        )

    }

    private fun observeViewModel() {
        viewModel.imageList.observe(viewLifecycleOwner,
            Observer {
                adapter.submitList(it)
            })
        
        viewModel.cameras.observe(viewLifecycleOwner,
            Observer {
                setNewCameras(it)
            })
    }


}