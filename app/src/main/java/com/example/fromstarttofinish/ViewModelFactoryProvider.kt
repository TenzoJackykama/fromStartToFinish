package com.example.fromstarttofinish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactoryProvider(val clientFactory : DataApi): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(clientFactory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}