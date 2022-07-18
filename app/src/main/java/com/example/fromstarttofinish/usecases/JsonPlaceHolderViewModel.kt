package com.example.fromstarttofinish.usecases

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.networking.dto.FakeData
import com.example.fromstarttofinish.networking.dto.JsonDataApi
import com.example.fromstarttofinish.usecases.model.FakeDataApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val KEY_FIRST_TIME_USER = "first_time_user"

class JsonPlaceHolderViewModel(private val retrofitReadyService: ClientRetrofit, preferance: SharedPreferences): ViewModel() {

    val _liveData: MutableLiveData<List<FakeDataApiModel>> = MutableLiveData<List<FakeDataApiModel>>()
    val liveData: MutableLiveData<List<FakeDataApiModel>>
        get() = _liveData

    fun retrievData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _liveData.value = retrofitReadyService.getServiceApi()
                Log.d("MainViewModel", "live data : $liveData")
            }catch (e: Exception){
                Log.d("MainViewModel", "$e")
            }
        }
    }

}