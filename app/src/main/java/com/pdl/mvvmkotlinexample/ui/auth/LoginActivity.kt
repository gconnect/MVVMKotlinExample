package com.pdl.mvvmkotlinexample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pdl.mvvmkotlinexample.MainActivity
import com.pdl.mvvmkotlinexample.R
import com.pdl.mvvmkotlinexample.data.entities.User
import com.pdl.mvvmkotlinexample.databinding.ActivityLoginBinding
import com.pdl.mvvmkotlinexample.util.hide
import com.pdl.mvvmkotlinexample.util.show
import com.pdl.mvvmkotlinexample.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware{

    override val kodein by kodein()
    private val factory : AuthViewModelFacory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding : ActivityLoginBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLogedInUser().observe(this, Observer {  user ->
            if(user != null){
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        toast("${user.name} is logged in")

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast("Login Failure")
    }

}
