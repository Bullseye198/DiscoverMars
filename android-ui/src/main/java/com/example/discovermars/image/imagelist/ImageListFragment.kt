package com.example.discovermars.image.imagelist

import android.app.DatePickerDialog
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.discovermars.R
import com.example.discovermars.common.startWithFade
import com.example.discovermars.dependencyInjection.ViewModelFactory
import com.example.discovermars.image.DateFormatterModule
import com.example.discovermars.image.ImageListViewModel
import com.example.discovermars.image.ImageState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_image_list.*
import kotlinx.android.synthetic.main.fragment_image_list.view.*
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class ImageListFragment : DaggerFragment() {

    private lateinit var viewModel: ImageListViewModel
    private lateinit var adapter: ImageListAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    private val calendar: Calendar = Calendar.getInstance()
    private var cyear = calendar.get(Calendar.YEAR)
    private var cmonth = calendar.get(Calendar.MONTH)
    private var cday = calendar.get(Calendar.DAY_OF_MONTH)
    private var isOpen = false

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
        onStart()
        setUpImageListAdapter()
        setupSpinnerAdapter()
        observeViewModel()
        onDateSelected()
        setupDropdownMenu()
    }

    override fun onStart() {
        super.onStart()
        (imv_mars_background.drawable as AnimationDrawable).startWithFade()

    }

    private fun setupSpinnerAdapter() {
        val cameras = mutableListOf<String>()

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

    private fun setupDropdownMenu() {
        isclicked(false)

        floatingActionButton6.setOnClickListener {
            isOpen = if (isOpen) {
                isclicked(false)
                closeDropdownAnimation()
                false
            } else{
                isclicked(true)
                openDropdownAnimation()
                true
            }

            dropdownCard1.setOnClickListener {
                isclicked(false)
                closeDropdownAnimation()
                viewModel.onRoverSelected("Spirit")
            }

            dropdownCard2.setOnClickListener {
                isclicked(false)
                closeDropdownAnimation()
                viewModel.onRoverSelected("Opportunity")
            }

            dropdownCard3.setOnClickListener {
                isclicked(false)
                closeDropdownAnimation()
                viewModel.onRoverSelected("Curiosity")
            }
        }
    }

   private fun isclicked(isClicked: Boolean) {

        dropdownCard1.isVisible = isClicked
        dropdownCard2.isVisible = isClicked
        dropdownCard3.isVisible = isClicked

    }

    private fun openDropdownAnimation() {
        val mFabOpenAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open)
        dropdownCard1.animation = mFabOpenAnim
        dropdownCard2.animation = mFabOpenAnim
        dropdownCard3.animation = mFabOpenAnim

    }

    private fun closeDropdownAnimation() {
        val mFabCloseAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close)
        dropdownCard1.animation = mFabCloseAnim
        dropdownCard2.animation = mFabCloseAnim
        dropdownCard3.animation = mFabCloseAnim
    }


    private fun setNewCameras(cameras: List<String>) {
        spinnerAdapter.clear()
        spinnerAdapter.addAll(cameras)
    }

    private fun onDateSelected() {
        BtnPickerDate.text = LocalDate.now().toString()
        BtnPickerDate.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    cyear = year
                    cmonth = monthOfYear
                    cday = dayOfMonth

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

        viewModel.getState().observe(viewLifecycleOwner,
        Observer { t ->
            if (t != null) {
               progressBar.isVisible = t.loading && t.feed.isNullOrEmpty()
                adapter.submitList(t.feed)
                t.cameras?.let { setNewCameras(it) }
            }
        })
    }
}