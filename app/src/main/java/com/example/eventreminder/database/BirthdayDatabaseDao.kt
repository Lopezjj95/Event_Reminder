package com.example.eventreminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Data access object which provides convenience methods for interacting with the database

 * insert(): Add new birthday to birthday table
 * update(): Change the value of a birthday in the table
 * get(): Return a birthday in the table
 * getAllBirthdays(): Return list of all added birthdays (ordered by id for now)
 * clear(): Remove all birthdays from table
 */
@Dao
interface BirthdayDatabaseDao {
    @Insert
    fun insert(birthday: Birthday)

    @Update
    fun update(birthday: Birthday)

    @Query("SELECT * FROM birthday_table WHERE birthdayId = :key")
    fun get(key: Long): Birthday?

    // TODO: Change getAllBirthdays() to order by birth date
    @Query("SELECT * FROM birthday_table ORDER BY birthdayId DESC")
    fun getAllBirthdays(): LiveData<List<Birthday>>

    @Query("DELETE FROM birthday_table")
    fun clear()
}