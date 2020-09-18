package com.example.loginsignup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginsignup.viewModels.LoginVM

class LoginFactory(private var context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(LoginVM::class.java)){
            return LoginVM(context) as T
        }
        throw IllegalAccessException(context.getString(R.string.throw_exception))
    }
}