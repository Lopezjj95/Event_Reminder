package com.example.eventreminder.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.eventreminder.R
import com.example.eventreminder.database.BirthdayDatabase
import com.example.eventreminder.databinding.FragmentFormBinding

/**
 * Implementation of Form Fragment's submit button
 */
@Suppress("DEPRECATION")
class FormFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views
        val binding = DataBindingUtil.inflate<FragmentFormBinding>(inflater,
            R.layout.fragment_form, container, false)

        // get reference to application context that this fragment is attached to
        val application = requireNotNull(this.activity).application
        // get reference to DAO
        val dataSource = BirthdayDatabase.getInstance(application).birthdayDatabaseDao
        // create instance of HomeViewModelFactory and pass refs to app context and dao
        val viewModelFactory = FormViewModelFactory(dataSource, application)

        // get reference to form ViewModel and assign binding variable to view model
        val formViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(FormViewModel::class.java)
        binding.formViewModel = formViewModel

        // change listeners to get text from name input fields
        var nameInputText = ""
        binding.nameInputText.addTextChangedListener {
            nameInputText = binding.nameInputText.text.toString()
        }

        var birthdayInputText = ""
        binding.birthdayInputText.addTextChangedListener {
            birthdayInputText = binding.birthdayInputText.text.toString()
        }

        var phoneInputText = ""
        binding.phoneInputText.addTextChangedListener {
            phoneInputText = binding.phoneInputText.text.toString()
        }

        // when the submit button is pressed, send text values to view model
        binding.buttonFormSubmit.setOnClickListener {
            formViewModel.onFormSubmit(name = nameInputText, birthday = birthdayInputText, phoneNumber = phoneInputText)
        }

        // observe _navigateToHome LiveData from view model
        formViewModel.navigateToHome.observe(viewLifecycleOwner, Observer {
            // if navigateToHome is true, navigate to home fragment
            if(it == true) {
                this.findNavController().navigate(
                    FormFragmentDirections.actionFormFragmentToHomeFragment())
                // reset navigateToHome to null
                formViewModel.doneNavigating()
            }
        })

        // set current activity as the lifecycle owner of the binding object
        binding.lifecycleOwner = this


        return binding.root
    }
}
