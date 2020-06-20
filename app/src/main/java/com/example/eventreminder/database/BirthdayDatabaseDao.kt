package com.example.eventreminder.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Data access object which provides convenience methods for interacting with the database

 * insert(): Add new birthday to birthday table
 * update(): Change the value of a birthday in the table
 * delete(): Remove a birthday from the list
 * get(): Return a birthday in the table
 * getAllBirthdays(): Return list of all added birthdays (ordered by birthday)
 * clear(): Remove all birthdays from table
 */
@Dao
interface BirthdayDatabaseDao {
    @Insert
    fun insert(birthday: Birthday)

    @Update
    fun update(birthday: Birthday)

    @Delete
    fun delete(birthday: Birthday)

    @Query("SELECT * FROM birthday_table WHERE birthdayId = :key")
    fun get(key: Long): Birthday?

    @Query("SELECT * FROM birthday_table ORDER BY birthday ASC")
    fun getAllBirthdays(): LiveData<List<Birthday>>

    @Query("DELETE FROM birthday_table")
    fun clear()
}