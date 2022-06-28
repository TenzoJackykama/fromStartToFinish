package com.example.fromstarttofinish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelFactory = ViewModelFactoryProvider(ClientRetrofit.getServiceApi())
        viewModel = viewModelFactory.create(MainViewModel::class.java)

        viewModel.liveData.observe(this) {
            Log.d("MainActivity", "${viewModel.liveData.value}")
        }

        viewModel.retrievData()

    }

}