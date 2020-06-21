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

    // allows for cancelling of coroutines started by this view model when view is destroyed
    private var viewModelJob = Job()
    // run coroutines on the main thread
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // launch coroutine to create new Birthday object and call insert() when form submit is pushed
    fun onFormSubmit() {
        uiScope.launch {
            val newBirthday = Birthday()
            insert(newBirthday)
        }
    }

    // suspend and switch to I/O context so the main thread isn't blocked
    // TODO: Insert input values from text fields instead of defaults
    private suspend fun insert(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            // call insert DAO method to insert new Birthday object into database
            database.insert(birthday)
        }
    }

    // cancel all coroutines when view model is destroyed
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}