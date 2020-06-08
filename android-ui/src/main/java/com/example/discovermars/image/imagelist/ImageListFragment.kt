package com.example.discovermars.image.imagelist

import android.app.DatePickerDialog
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.discovermars.R
import com.example.discovermars.common.startWithFade
import com.example.discovermars.databinding.FragmentImageListBinding
import com.example.discovermars.dependencyInjection.ViewModelFactory
import com.example.discovermars.image.DateFormatterModule
import dagger.android.support.DaggerFragment
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class ImageListFragment : DaggerFragment() {

    private lateinit var viewModel: ImageListViewModel
    private lateinit var adapter: ImageListAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private lateinit var binding: FragmentImageListBinding

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
        binding = FragmentImageListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ImageListViewModel::class.java)
        return binding.root
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
        (binding.imvMarsBackground.drawable as AnimationDrawable).startWithFade()
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

        binding.floatingActionButton6.setOnClickListener {
            isOpen = if (isOpen) {
                isclicked(false)
                closeDropdownAnimation()
                false
            } else {
                isclicked(true)
                openDropdownAnimation()
                true
            }

            binding.dropdownCard1.setOnClickListener {
                isclicked(false)
                closeDropdownAnimation()
                viewModel.onRoverSelected("Spirit")
            }

            binding.dropdownCard2.setOnClickListener {
                isclicked(false)
                closeDropdownAnimation()
                viewModel.onRoverSelected("Opportunity")
            }

            binding.dropdownCard3.setOnClickListener {
                isclicked(false)
                closeDropdownAnimation()
                viewModel.onRoverSelected("Curiosity")
            }
        }
    }

    private fun isclicked(isClicked: Boolean) {

        binding.dropdownCard1.isVisible = isClicked
        binding.dropdownCard2.isVisible = isClicked
        binding.dropdownCard3.isVisible = isClicked

    }

    private fun openDropdownAnimation() {
        val mFabOpenAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open)
        binding.dropdownCard1.animation = mFabOpenAnim
        binding.dropdownCard2.animation = mFabOpenAnim
        binding.dropdownCard3.animation = mFabOpenAnim

    }

    private fun closeDropdownAnimation() {
        val mFabCloseAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close)
        binding.dropdownCard1.animation = mFabCloseAnim
        binding.dropdownCard2.animation = mFabCloseAnim
        binding.dropdownCard3.animation = mFabCloseAnim
    }


    private fun setNewCameras(cameras: List<String>) {
        spinnerAdapter.clear()
        spinnerAdapter.addAll(cameras)
    }

    private fun onDateSelected() {
        binding.BtnPickerDate.text = LocalDate.now().toString()
        binding.BtnPickerDate.setOnClickListener {

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

                    binding.BtnPickerDate.text = formattedDate
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
        binding.recListFragment.adapter = null
        binding.spinner1.adapter = null
    }

    private fun setUpImageListAdapter() {
        adapter = ImageListAdapter()
        binding.recListFragment.adapter = adapter
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