package com.example.fromstarttofinish

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.usecases.AppDatabase
import com.example.fromstarttofinish.usecases.JsonPlaceHolderViewModel
import com.example.myapplication.github.repository.dao.RepoDao

class ViewModelFactoryProvider(val clientFactory: ClientRetrofit, val preferance: SharedPreferences,  private val database: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(JsonPlaceHolderViewModel::class.java)) {
            return JsonPlaceHolderViewModel(clientFactory, preferance, database.repoDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}