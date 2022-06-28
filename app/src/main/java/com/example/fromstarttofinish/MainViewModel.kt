package com.example.fromstarttofinish

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainViewModel(private val retrofit: DataApi): ViewModel() {

    val _liveData: MutableLiveData<FakeData> = MutableLiveData<FakeData>()
    val liveData: MutableLiveData<FakeData>
        get() = _liveData

    fun retrievData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _liveData.value= retrofit.retrivePosts()
                Log.d("MainViewModel", "$liveData")
            }catch (e: Exception){
                Log.d("MainViewModel", "$e")
            }
        }
    }

}