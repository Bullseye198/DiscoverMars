package com.example.discovermars.image.imagelist

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.discovermars.R
import com.example.discovermars.dependencyInjection.ViewModelFactory
import com.example.discovermars.image.DateFormatterModule
import com.example.discovermars.image.ImageListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_image_list.*
import java.time.LocalDate
import java.util.*
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
        BtnPickerDate.text = LocalDate.now().toString()
        BtnPickerDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val cyear = calendar.get(Calendar.YEAR)
            val cmonth = calendar.get(Calendar.MONTH)
            val cday = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    DateFormatterModule.onDateFormatted(year, monthOfYear, dayOfMonth)
                    val formattedDate =
                        DateFormatterModule.onDateFormatted(year, monthOfYear, dayOfMonth)
                    viewModel.onDateSelected(formattedDate)

                    BtnPickerDate.text = formattedDate
                },
                cyear,
                cmonth,
                cday
            )
            datePickerDialog.show()
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
        val progressBar = requireView().findViewById<ProgressBar>(R.id.imgProgressBar)

        viewModel.loading.observe(viewLifecycleOwner,
            Observer { t ->
                if (t != null) {
                    progressBar.isVisible = t
                }
            })

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