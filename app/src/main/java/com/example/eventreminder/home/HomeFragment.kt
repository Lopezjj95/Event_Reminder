package com.example.eventreminder.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.eventreminder.R
import com.example.eventreminder.database.BirthdayDatabase
import com.example.eventreminder.databinding.FragmentHomeBinding

/**
 * Implementation of home fragment's FAB and birthday list
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home, container, false)

        // Navigate to Form fragment when Add Event FAB is pushed
        binding.fabAddEvent.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_HomeFragment_to_FormFragment)
        }

        // get reference to application context
        val application = requireNotNull(this.activity).application
        // get reference to DAO
        val dataSource = BirthdayDatabase.getInstance(application).birthdayDatabaseDao
        // create instance of HomeViewModelFactory and pass refs to app context and dao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)

        // get reference to home ViewModel and assign binding variable to view model
        val homeViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel

        // set current activity as the lifecycle owner of the binding object
        binding.lifecycleOwner = this


        return binding.root
    }
}