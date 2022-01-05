package com.saifkhan.completeappdemo.Ui.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saifkhan.productdbapp.MainRepository

class HomeViewModelFactory(
    val appRepository: MainRepository,
    val context:Context
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(appRepository,context) as T

        }
        throw IllegalArgumentException("Unknown class name")

    }
}