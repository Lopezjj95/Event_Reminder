package com.example.eventreminder.home

import android.app.Application
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.eventreminder.R
import com.example.eventreminder.database.Birthday
import com.example.eventreminder.database.BirthdayDatabaseDao

/**
 * ViewModel for HomeFragment
 */
class HomeViewModel(
    private val database: BirthdayDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    // get all birthdays from database
    val birthdays = database.getAllBirthdays()
    // reformat birthday list to string for display
    val birthdaysString = Transformations.map(birthdays) { birthdays ->
        formatBirthdays(birthdays, application.resources)
    }
}

// Method that reformats birthday list data for display
fun formatBirthdays(birthdays: List<Birthday>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.title))
        birthdays.forEach {
            append("<br>")
            append(resources.getString(R.string.entry_name))
            append("\t${it.name}<br>")
            append(resources.getString(R.string.entry_birthday))
            append("\t${it.birthday}<br>")
            // only display phone number if the user enters one
            if(it.phoneNumber != "") {
                append(resources.getString(R.string.entry_phone))
                append("\t${it.phoneNumber}<br>")
            }
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
