package com.example.loginsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.loginsignup.databinding.ActivityLoginBinding
import com.example.loginsignup.retrofit.ApiInterface
import com.example.loginsignup.retrofit.RetrofitClient
import com.example.loginsignup.viewModels.LoginVM
import retrofit2.create

class LoginActivity : AppCompatActivity()
{

    private lateinit var loginViewModel: LoginVM
    private lateinit var activityLoginBinding: ActivityLoginBinding
    private lateinit var loginFactory: LoginFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLoginBinding  = DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginFactory = LoginFactory(this)
        loginViewModel = ViewModelProvider(this,loginFactory).get(LoginVM::class.java)
        activityLoginBinding.loginVM = loginViewModel

    }
}