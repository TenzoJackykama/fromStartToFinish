package com.example.fromstarttofinish.usecases

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.usecases.model.FakeDataApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch



class JsonPlaceHolderViewModel(private val retrofitReadyService: ClientRetrofit, val preferences:SharedPreferences): ViewModel() {

    val apiCallResult = MutableSharedFlow<List<FakeDataApiModel>>()

   fun retrievData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // emint the event need coroutine menagment
                viewModelScope.launch {
                    apiCallResult.emit(retrofitReadyService.getServiceApi())
                }

                Log.d("MainViewModel", "mutable : ${apiCallResult.toString()}")


            }catch (e: Exception){
                Log.d("MainViewModel", "error $e")
            }
        }
    }
}