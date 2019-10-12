package com.pdl.mvvmkotlinexample.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pdl.mvvmkotlinexample.data.entities.Quotes
import com.pdl.mvvmkotlinexample.data.localDb.AppDatabase
import com.pdl.mvvmkotlinexample.data.network.MyApi
import com.pdl.mvvmkotlinexample.data.network.responses.SafeApiRequest
import com.pdl.mvvmkotlinexample.data.preferences.PreferenceProvider
import com.pdl.mvvmkotlinexample.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL = 6

class QuoteRepository(
    private val api : MyApi,
    private val db : AppDatabase,
    private val prefs : PreferenceProvider
): SafeApiRequest(){
    private val quotes = MutableLiveData<List<Quotes>>()

    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes : List<Quotes>) {
        Coroutines.io{
            prefs.saveLastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }

    suspend fun getQuotes() : LiveData<List<Quotes>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            //This will get quotes from the db
            db.getQuoteDao().getQuotes()
        }
    }
    private suspend fun fetchQuotes(){
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt ==null || isFetchNeeded(LocalDateTime.parse(lastSavedAt)))
            var response = apiRequest{
            api.getQuotes()
        }
        quotes.postValue(response.quotes)

    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }
}