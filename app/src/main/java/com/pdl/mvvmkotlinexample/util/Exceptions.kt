package com.pdl.mvvmkotlinexample.util

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetConnection(message: String) : IOException(message)