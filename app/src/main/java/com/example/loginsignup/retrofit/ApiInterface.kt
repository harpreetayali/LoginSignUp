package com.example.loginsignup.retrofit

import com.example.loginsignup.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface
{
    @FormUrlEncoded
    @POST(Urls.LOG_IN)
    suspend fun login(
        @Field("name") email : String,
        @Field("job") password: String) : Response<LoginResponse>


}