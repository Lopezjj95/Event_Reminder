package com.example.eventreminder.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventreminder.database.Birthday
import com.example.eventreminder.database.BirthdayDatabaseDao
import kotlinx.coroutines.*

/**
 * ViewModel for FormFragment
 */
class FormViewModel(
    private val database: BirthdayDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Method called when form submit button is clicked
    fun onStartTracking() {
        uiScope.launch {
            val newBirthday = Birthday()
            insert(newBirthday)
        }
    }

    // Method to insert new birthday into database
    // TODO: Insert input values from text fields instead of defaults
    private suspend fun insert(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            database.insert(birthday)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}