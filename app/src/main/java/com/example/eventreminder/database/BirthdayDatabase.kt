package com.example.eventreminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/** Database that the birthdays are stored in */
@Database(entities = [Birthday::class], version = 2, exportSchema = false)
abstract class BirthdayDatabase : RoomDatabase() {

    abstract val birthdayDatabaseDao: BirthdayDatabaseDao

    /*
     * allows clients to access the methods for creating or getting the database without
     * instantiating the class
     */
    companion object {

        // creates reference to database to avoid repeatedly opening connections to the database
        @Volatile
        private var INSTANCE: BirthdayDatabase? = null

        // method to get instance of database
        fun getInstance(context: Context): BirthdayDatabase {
            // ensures that database is only initialized once
            synchronized(this) {
                var instance = INSTANCE

                // if database does not exist, create one
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BirthdayDatabase::class.java,
                        "birthday_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}