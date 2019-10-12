package com.pdl.mvvmkotlinexample.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.pdl.mvvmkotlinexample.data.repositories.UserRespository
import com.pdl.mvvmkotlinexample.util.ApiException
import com.pdl.mvvmkotlinexample.util.Coroutines
import com.pdl.mvvmkotlinexample.util.NoInternetConnection

class AuthViewModel(private val respository: UserRespository) : ViewModel(){
    var name : String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null
    var authListener : AuthListener? = null

    fun getLogedInUser() = respository.getUser()

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){

            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main{
           try{
               val authResponse = respository.userLogin(email!!, password!!)
               authResponse.user?.let {
                   authListener?.onSuccess(it)
                   respository.saveUser(it)
                   return@main
               }
           }catch (e : NoInternetConnection){
               authListener?.onFailure(e.message!!)
           }
        }

    }

    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignup(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    //signup
    fun onSignupButtonClick(view : View){
        authListener?.onStarted()

        if(name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }

        if(email.isNullOrEmpty()){
            authListener?.onFailure("Email is required")
            return
        }

        if(password.isNullOrEmpty()){
            authListener?.onFailure("please enter a password")
            return
        }

        if(password != confirmPassword){
            authListener?.onFailure("Password did not match")
            return
        }

        Coroutines.main{
            try{
                val authResponse = respository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    respository.saveUser(it)
                    return@main
                }
            }catch (e : NoInternetConnection){
                authListener?.onFailure(e.message!!)
            }
        }

    }
}