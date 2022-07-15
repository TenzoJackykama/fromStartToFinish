package com.example.fromstarttofinish

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.networking.dto.FakeData
import com.example.fromstarttofinish.networking.dto.JsonDataApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JsonPlaceHolderViewModel(private val retrofitReadyService: ClientRetrofit): ViewModel() {

    val _liveData: MutableLiveData<FakeData> = MutableLiveData<FakeData>()
    val liveData: MutableLiveData<FakeData>
        get() = _liveData

    fun retrievData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _liveData.value = retrofitReadyService.getServiceApi()
                Log.d("MainViewModel", "$liveData")
            }catch (e: Exception){
                Log.d("MainViewModel", "$e")
            }
        }
    }

}