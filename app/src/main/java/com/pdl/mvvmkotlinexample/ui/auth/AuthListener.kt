package com.pdl.mvvmkotlinexample.ui.auth

import androidx.lifecycle.LiveData
import com.pdl.mvvmkotlinexample.data.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message : String)
}