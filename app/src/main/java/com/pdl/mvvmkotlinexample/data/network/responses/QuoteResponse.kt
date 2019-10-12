package com.pdl.mvvmkotlinexample.data.network.responses

import com.pdl.mvvmkotlinexample.data.entities.Quotes

data class QuoteResponse(
    val isSuccessful : Boolean,
    val quotes : List<Quotes>
)