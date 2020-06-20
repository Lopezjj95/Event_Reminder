package com.example.eventreminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines the data table and the birthday objects that will be stored in the table. Each instance
 * of this class represents a row of data in the table and each property defines a column.
 */
@Entity(tableName = "birthday_table")

data class Birthday(
    // Set birthdayId to be the primary key and generate unique id for each entity
    @PrimaryKey(autoGenerate = true)
    var birthdayId: Long = 0L,

    var name: String = "New Entry Name",

    var birthday: String = "New Entry Birthday",

    @ColumnInfo(name = "phone_number")
    var phoneNumber: String = "New Entry Phone Number"
)