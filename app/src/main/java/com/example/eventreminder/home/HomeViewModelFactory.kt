package com.example.eventreminder.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventreminder.database.BirthdayDatabaseDao

/**
 * Factory Class to create Home ViewModel Instances
 */
class HomeViewModelFactory(
        private val dataSource: BirthdayDatabaseDao,
        private val application: Application) : ViewModelProvider.Factory {

    // method takes any class type as an argument and returns  a HomeViewModel
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // checks if there is a HomeViewModel class available and returns instance if there is
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}