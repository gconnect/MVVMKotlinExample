package com.pdl.mvvmkotlinexample.ui.auth.home

import androidx.lifecycle.ViewModel
import com.pdl.mvvmkotlinexample.data.repositories.UserRespository

class ProfileViewModel( repository: UserRespository) : ViewModel() {
    // TODO: Implement the ViewModel

    val user = repository.getUser()
}
