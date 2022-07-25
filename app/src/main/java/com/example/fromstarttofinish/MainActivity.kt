package com.example.fromstarttofinish

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.fromstarttofinish.networking.AuthorizationInterceptor
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.usecases.JsonPlaceHolderViewModel
import com.example.fromstarttofinish.usecases.model.FakeDataApiModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:JsonPlaceHolderViewModel
    lateinit var viewModelFactory: ViewModelFactoryProvider
    lateinit var preferance: SharedPreferences
    val authorizationInterceptor = AuthorizationInterceptor()
    var dataList = mutableListOf<FakeDataApiModel>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferance = getPreferences(Context.MODE_PRIVATE)
        viewModelFactory = ViewModelFactoryProvider(ClientRetrofit())
        viewModel = viewModelFactory.create(JsonPlaceHolderViewModel::class.java)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val clientHttp = OkHttpClient.Builder().addInterceptor(logging).addInterceptor(authorizationInterceptor).build()
        observerJsonFakeApi()

        viewModel.retrievData()




    }

    override fun onResume() {
        super.onResume()
        for(i in 0 until dataList.size){
            Log.d("MainActivity print", "data list print 1b1${dataList.get(i)}")
        }
        // for seeing complete network comunication

    }
    fun observerJsonFakeApi(){
        lifecycleScope.launch {
        viewModel.apiCallResult.collect {
            Log.d("MainActivity", "${it}")
            Log.d("MainActivity", "${it.size}")
            dataList = it as MutableList<FakeDataApiModel>
            Log.d("MainActivity", "data list : ${dataList.toString()}")
            }
        }
    }


}