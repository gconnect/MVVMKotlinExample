package com.pdl.mvvmkotlinexample.data.UserDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdl.mvvmkotlinexample.data.entities.CURRENT_USER_ID
import com.pdl.mvvmkotlinexample.data.entities.User

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(user : User) : Long

    @Query("SELECT * FROM user WHERE uid= $CURRENT_USER_ID")
    fun getUser() : LiveData<User>


}