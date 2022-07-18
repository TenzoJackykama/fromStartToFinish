package com.example.fromstarttofinish.usecases

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.usecases.model.FakeDataApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ClassCastException
import kotlin.random.Random

const val KEY_FIRST_TIME_USER = "first_time_user"

class JsonPlaceHolderViewModel(private val retrofitReadyService: ClientRetrofit, val preferences: SharedPreferences): ViewModel() {

    val _liveData: MutableLiveData<List<FakeDataApiModel>> = MutableLiveData<List<FakeDataApiModel>>()
    val liveData: MutableLiveData<List<FakeDataApiModel>>
        get() = _liveData

    fun retrievData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _liveData.value = retrofitReadyService.getServiceApi()
                Log.d("MainViewModel", "live data : $liveData")
                for (i in 0..liveData.value!!.size){
                    Log.d("MainViewModel", "print : ${liveData.value!!.get(i)}")
                    //see https://developer.android.com/reference/android/content/SharedPreferences.Editor#commit() please
                    preferences.edit().putInt(liveData.value!!.get(i).title, liveData.value!!.get(i).id).apply()

                }
            }catch (e: Exception){
                Log.d("MainViewModel", "$e")
            }
        }
    }

    fun testSharedPreferencesReading(){
        val random = Random.nextInt(0, 100)
        Log.d("MainViewModel", "random number gen : $random")
        val randomFakeData = liveData.value?.get(random)
        Log.d("MainViewModel", "Random FakeDataItem: ${randomFakeData.toString()}")
        val defValue = -1
        try {
            val classRetrivedIndex = preferences.getInt(randomFakeData!!.title, -1)
            Log.d("MainViewModel", "FakeDataItemIfExist : ${liveData.value!!.get(classRetrivedIndex).toString()}")

        }catch (e : ClassCastException){
            Log.d("MainViewModel", "not int value : $e")
        }
    }

}