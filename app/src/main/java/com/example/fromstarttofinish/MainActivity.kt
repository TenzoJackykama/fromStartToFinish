package com.example.fromstarttofinish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            try {
                val retrofit = ClientRetrofit.getServiceApi()
                val posts = retrofit.retrivePosts()
                Log.d("MainActivity", "$posts")

            }catch (e: Exception){
                Log.d("MainActivity", "$e")
            }
        }
    }
}