package com.example.fromstarttofinish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.networking.dto.FakeData
import com.example.fromstarttofinish.networking.dto.JsonDataApi

class ViewModelFactoryProvider(val clientFactory: ClientRetrofit): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(JsonPlaceHolderViewModel::class.java)) {
            return JsonPlaceHolderViewModel(clientFactory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}