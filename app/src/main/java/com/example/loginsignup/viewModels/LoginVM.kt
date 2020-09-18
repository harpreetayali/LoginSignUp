package com.example.loginsignup.viewModels

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.loginsignup.response.LoginResponse
import com.example.loginsignup.retrofit.ApiInterface
import com.example.loginsignup.retrofit.RequestProcess
import com.example.loginsignup.retrofit.RetrofitClient
import retrofit2.Response

class LoginVM(context: Context) : ViewModel()
{
    var name: String = ""
    var job: String = ""



     fun loginService(view:View) {
        RetrofitClient.callService(true,"",object :RequestProcess<Response<LoginResponse>>{
            override suspend fun sendRequest(apiInterface: ApiInterface): Response<LoginResponse> {
                return apiInterface.login(name,job)
            }

            override fun onResponse(res: Response<LoginResponse>)
            {
                if (res.isSuccessful) {
                    Log.i("response","${res.body()?.name}")
                }
            }

            override fun onException(message: String) {

            }

        })
    }
}