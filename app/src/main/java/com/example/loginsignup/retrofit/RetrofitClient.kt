package com.example.loginsignup.retrofit

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.time.chrono.MinguoChronology
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val BASE_URL = "https://reqres.in/"

    fun <T> callService(

        dialogFlag: Boolean,
        token: String,
        requestProcess: RequestProcess<T>
    ) {
        var okHttpClient: OkHttpClient? = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.MINUTES)
            .connectTimeout(10, TimeUnit.MINUTES)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)

        val coRoutineExpHandler = CoroutineExceptionHandler { _, t ->
            t.printStackTrace()

            CoroutineScope(Dispatchers.Main).launch {

                t.message?.let { requestProcess.onException(it) }

            }
        }

        CoroutineScope(Dispatchers.IO + coRoutineExpHandler).launch {

            val response = requestProcess.sendRequest(apiInterface) as Response<*>

            CoroutineScope(Dispatchers.Main).launch {

                requestProcess.onResponse(response as T)

                if (response.isSuccessful) {

                    Log.i("responseCo",response.body().toString())

                }
            }
        }
    }


}