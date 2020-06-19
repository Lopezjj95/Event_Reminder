package com.example.eventreminder.form

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.eventreminder.R
import com.example.eventreminder.database.BirthdayDatabase
import com.example.eventreminder.databinding.FragmentFormBinding

/**
 * Implementation of Form Fragment's submit button
 */
class FormFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentFormBinding>(inflater,
            R.layout.fragment_form, container, false)

        // Navigate back to Home Fragment when Form Submit Button is pushed
        binding.buttonFormSubmit.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_FormFragment_to_HomeFragment)
        }

        val application = requireNotNull(this.activity).application
        // get reference to DAO
        val dataSource = BirthdayDatabase.getInstance(application).birthdayDatabaseDao
        val viewModelFactory = FormViewModelFactory(dataSource, application)

        // get reference to form ViewModel
        val formViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(FormViewModel::class.java)
        binding.formViewModel = formViewModel

        binding.setLifecycleOwner(this)


        return binding.root
    }
}