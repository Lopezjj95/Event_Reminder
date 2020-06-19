package com.example.eventreminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines the table that will store the birthday objects and their properties in the
 * database. Each instance of this class represents a row in the table and each property defines a
 * column.
 */
@Entity(tableName = "birthday_table")

data class Birthday(
    // Set birthdayId to be the primary key and generate unique id for each entity
    @PrimaryKey(autoGenerate = true)
    var birthdayId: Long = 0L,

    @ColumnInfo(name = "entry_name")
    var entryName: String = "New Entry Name",

    @ColumnInfo(name = "entry_birthday")
    var entryBirthday: String = "New Entry Birthday",

    @ColumnInfo(name = "entry_phone_number")
    var entryPhoneNumber: String = "New Entry Phone Number"
)