package com.pdl.mvvmkotlinexample.ui.auth.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.pdl.mvvmkotlinexample.R
import com.pdl.mvvmkotlinexample.databinding.ProfileFragmentBinding
import okhttp3.internal.Internal.instance
import org.kodein.di.KodeinAware

class ProfileFragment : Fragment(), KodeinAware {

    override val Kodein by kodein()

    private lateinit var viewModel: ProfileViewModel

    private val factory : ProfileViewModelFacory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleowner = this
        return binding.root
    }

}
