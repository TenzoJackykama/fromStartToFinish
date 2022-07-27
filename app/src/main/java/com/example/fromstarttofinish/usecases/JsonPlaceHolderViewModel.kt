package com.example.fromstarttofinish.usecases

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fromstarttofinish.networking.ClientRetrofit
import com.example.fromstarttofinish.usecases.model.FakeDataApiModel
import com.example.myapplication.github.repository.dao.RepoDao
import com.example.myapplication.github.repository.entity.toEntity
import com.example.myapplication.github.repository.entity.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class JsonPlaceHolderViewModel(private val retrofitReadyService: ClientRetrofit,
                               val preferences:SharedPreferences,
                               private val repoDao:RepoDao
): ViewModel() {

    val apiCallResult = MutableSharedFlow<List<FakeDataApiModel>>()

   fun retrievData() {
       viewModelScope.launch {
           try {
               // emint the event need coroutine menagment

               apiCallResult.emit(retrofitReadyService.getServiceApi())
               val dataFromNetwork = retrofitReadyService.getServiceApi()

               repoDao.insertAll(*dataFromNetwork.map { repo -> repo.toEntity() }.toTypedArray())
               Log.d("MainViewModel", "mutable : ${apiCallResult.toString()}")

           } catch (e:Exception) {
               Log.d("MainViewModel", "error $e")
           }
       }
   }

    private fun setupDatabaseObserver() {
        viewModelScope.launch {
            repoDao.getAll().collect {
                Log.d("MainViewModel", "retrieved from database")
                apiCallResult.emit(it.map{ repoEntity -> repoEntity.toModel() })}
            }
    }

}