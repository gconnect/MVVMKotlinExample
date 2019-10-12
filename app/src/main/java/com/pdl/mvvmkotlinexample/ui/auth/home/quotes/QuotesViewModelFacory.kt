package com.pdl.mvvmkotlinexample.ui.auth.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdl.mvvmkotlinexample.data.repositories.QuoteRepository


@Suppress("UNCHECKED_CAST")
class QuotesViewModelFacory(private val repository: QuoteRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModelFacory(repository) as T
    }


}