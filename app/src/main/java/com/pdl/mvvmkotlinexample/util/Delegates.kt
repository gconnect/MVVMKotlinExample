package com.pdl.mvvmkotlinexample.util

import android.provider.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred

fun<T> lazyDeffered(block: suspend CoroutineScope.() -> T) : Lazy<Deferred<T>>{
    return lazy { Settings.GlobalScope.async(start = CoroutineStart.LAZY){
        block.invoke(this)
    } }
}