package com.pdl.mvvmkotlinexample.data.UserDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdl.mvvmkotlinexample.data.entities.Quotes

@Dao
interface QuoteDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(quotes : List<Quotes>)

    @Query("SELECT * FROM Quotes")
    fun getQuotes() : LiveData<List<Quotes>>
}