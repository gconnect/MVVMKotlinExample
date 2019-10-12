package com.pdl.mvvmkotlinexample.data.repositories


import com.pdl.mvvmkotlinexample.data.entities.User
import com.pdl.mvvmkotlinexample.data.localDb.AppDatabase
import com.pdl.mvvmkotlinexample.data.network.MyApi
import com.pdl.mvvmkotlinexample.data.network.responses.AuthResponse
import com.pdl.mvvmkotlinexample.data.network.responses.SafeApiRequest

class UserRespository(private val api:MyApi, private val db : AppDatabase) : SafeApiRequest(){

  suspend  fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest{api.userLogin(email, password)}
    }


  suspend  fun userSignup( name: String, email: String, password: String): AuthResponse {
    return apiRequest{api.userSignup(name, email, password)}
  }

    //performing asynchronous function
    suspend fun saveUser(user : User) = db.getUserDao().insert(user)

    fun getUser() = db.getUserDao().getUser()

}
