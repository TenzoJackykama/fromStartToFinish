package com.example.fromstarttofinish.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fromstarttofinish.JsonPlaceHolderViewModel
import com.example.fromstarttofinish.R
import com.example.fromstarttofinish.ViewModelFactoryProvider

class JsonPlaceHolder : AppCompatActivity() {

    lateinit var viewModel: JsonPlaceHolderViewModel

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelFactory = ViewModelFactoryProvider(ClientRetrofit.getServiceApi())
        viewModel = viewModelFactory.create(JsonPlaceHolderViewModel::class.java)

        viewModel.liveData.observe(this) {
            Log.d("MainActivity", "${viewModel.liveData.value}")
        }

        viewModel.retrievData()

    }

}