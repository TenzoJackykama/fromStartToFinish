package com.example.fromstarttofinish

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.networking.dto.FakeData
import com.example.fromstarttofinish.networking.dto.FakeDataItem
import com.example.fromstarttofinish.usecases.JsonPlaceHolderViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:JsonPlaceHolderViewModel
    lateinit var viewModelFactory: ViewModelFactoryProvider
    lateinit var preferance: SharedPreferences
    var dataList = mutableListOf<FakeData>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferance = getPreferences(Context.MODE_PRIVATE)
        viewModelFactory = ViewModelFactoryProvider(ClientRetrofit(), preferance)
        viewModel = viewModelFactory.create(JsonPlaceHolderViewModel::class.java)


        observerJsonFakeApi()
        viewModel.retrievData()

    }

    override fun onResume() {
        super.onResume()
        for(i in 0 until dataList.size){
            Log.d("MainActivity print", "data list print 1b1${dataList.get(i)}")
        }
    }
    fun observerJsonFakeApi(){
        viewModel.liveData.observe(this) {
            Log.d("MainActivity", "${viewModel.liveData.value}")
            Log.d("MainActivity", "${viewModel.liveData.value?.size}")
            dataList = (viewModel.liveData.value!! as MutableList<FakeData>)
            Log.d("MainActivity", "data list : ${dataList.toString()}")
            viewModel.testSharedPreferencesReading()
        }
    }


}