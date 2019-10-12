package com.pdl.mvvmkotlinexample.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdl.mvvmkotlinexample.data.repositories.UserRespository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFacory(private val repository: UserRespository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }


}