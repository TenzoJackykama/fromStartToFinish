package com.example.fromstarttofinish

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.usecases.JsonPlaceHolderViewModel

class ViewModelFactoryProvider(val clientFactory:ClientRetrofit, val preferance: SharedPreferences): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(JsonPlaceHolderViewModel::class.java)) {
            return JsonPlaceHolderViewModel(clientFactory, preferance) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}