package com.example.discovermars.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.discovermars.R
import com.example.discovermars.image.ImageDetailViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.fragment_image_list.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    lateinit var viewModel:  ImageDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var nav: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav = Navigation.findNavController(this, R.id.fragment_nav)






    }


}