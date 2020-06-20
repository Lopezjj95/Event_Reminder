package com.example.eventreminder

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eventreminder.database.Birthday
import com.example.eventreminder.database.BirthdayDatabase
import com.example.eventreminder.database.BirthdayDatabaseDao
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import java.io.IOException

/**
 * Test class for the Birthday Database
 *
 * Tests creating, inserting, and closing the database
 */
@RunWith(AndroidJUnit4::class)
class BirthdayDatabaseTest {

    private lateinit var birthdayDao: BirthdayDatabaseDao
    private lateinit var db: BirthdayDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, BirthdayDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        birthdayDao = db.birthdayDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insert() {
        val birthday = Birthday()
        birthdayDao.insert(birthday)
    }
}