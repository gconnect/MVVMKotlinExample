package com.pdl.mvvmkotlinexample.data.network.responses

import com.pdl.mvvmkotlinexample.data.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)