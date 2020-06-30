package com.example.eventreminder.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eventreminder.database.Birthday
import com.example.eventreminder.database.BirthdayDatabaseDao
import kotlinx.coroutines.*

/**
 * ViewModel for FormFragment
 * Stores and manages UI-related data
 */
class FormViewModel(
    private val database: BirthdayDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    // allows for cancelling of coroutines started by this view model when view is destroyed
    private var viewModelJob = Job()
    // run coroutines on the main thread
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // LiveData to signal when to navigate back to home fragment
    private val _navigateToHome = MutableLiveData<Boolean?>()
    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    // launch coroutine to create new Birthday object and call insert() when form submit is pushed
    fun onFormSubmit(name: String, birthday: String, phoneNumber: String) {
        uiScope.launch {
            val newBirthday = Birthday(name = name, birthday = birthday, phoneNumber = phoneNumber)
            insert(newBirthday)
            _navigateToHome.value = true
        }
    }

    // suspend and switch to I/O context so the main thread isn't blocked
    private suspend fun insert(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            // call insert DAO method to insert new Birthday object into database
            database.insert(birthday)
        }
    }

    fun doneNavigating() {
        _navigateToHome.value = false
    }

    // cancel all coroutines when view model is destroyed
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}