package com.example.fromstarttofinish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.usecases.JsonPlaceHolderViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:JsonPlaceHolderViewModel

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelFactory = ViewModelFactoryProvider(ClientRetrofit())
        viewModel = viewModelFactory.create(JsonPlaceHolderViewModel::class.java)


        observerJsonFakeApi()
        viewModel.retrievData()

    }

    fun observerJsonFakeApi(){
        viewModel.liveData.observe(this) {
            Log.d("MainActivity", "${viewModel.liveData.value}")
        }
    }

}