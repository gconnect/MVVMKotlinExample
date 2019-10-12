package com.pdl.mvvmkotlinexample

import android.app.Application
import com.pdl.mvvmkotlinexample.data.localDb.AppDatabase
import com.pdl.mvvmkotlinexample.data.network.MyApi
import com.pdl.mvvmkotlinexample.data.network.NetworkConnectionInterceptor
import com.pdl.mvvmkotlinexample.data.preferences.PreferenceProvider
import com.pdl.mvvmkotlinexample.data.repositories.QuoteRepository
import com.pdl.mvvmkotlinexample.data.repositories.UserRespository
import com.pdl.mvvmkotlinexample.ui.auth.AuthViewModelFacory
import com.pdl.mvvmkotlinexample.ui.auth.home.ProfileViewModelFacory
import org.kodein.di.Kodein.Companion.lazy
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class MVVMApplication : Application(), KodeinAware {
    override val kodein = lazy{
        import(androidXModule(this@MVVMApplication))
        bind() from singleton{ NetworkConnectionInterceptor(instance()) }
        bind() from singleton{ MyApi(instance()) }
        bind() from singleton{ AppDatabase(instance()) }
        bind() from singleton{ UserRespository(instance(), instance()) }
        bind() from singleton{ PreferenceProvider(instance()) }
        bind() from singleton{ QuoteRepository(instance(), instance())}
        bind() from provider{ AuthViewModelFacory(instance()) }
        bind() from provider{ ProfileViewModelFacory(instance()) }
    }
}