package com.pdl.mvvmkotlinexample.ui.auth.home.quotes

import androidx.lifecycle.ViewModel
import com.pdl.mvvmkotlinexample.data.repositories.QuoteRepository
import com.pdl.mvvmkotlinexample.util.lazyDeffered

class QuotesViewModel(repository : QuoteRepository) : ViewModel() {
    // TODO: Implement the ViewModel

    //run on couroutine asychronously
    val quotes by lazyDeffered{
        repository.getQuotes()
    }
}
