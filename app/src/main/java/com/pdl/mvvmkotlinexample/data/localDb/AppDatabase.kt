package com.pdl.mvvmkotlinexample.data.localDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdl.mvvmkotlinexample.data.UserDao.QuoteDao
import com.pdl.mvvmkotlinexample.data.UserDao.UserDao
import com.pdl.mvvmkotlinexample.data.entities.User
import com.pdl.mvvmkotlinexample.data.entities.Quotes

@Database(entities = [User::class, Quotes::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
    abstract fun getQuoteDao() : QuoteDao
    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "myDatabase"
        ).build()
    }
}