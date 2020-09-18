package com.example.loginsignup.retrofit

import retrofit2.Retrofit

interface RequestProcess<T>
{
    suspend fun sendRequest(apiInterface: ApiInterface):T

    fun onResponse(res: T)

    fun onException(message: String)
}