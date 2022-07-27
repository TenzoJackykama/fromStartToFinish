package com.example.fromstarttofinish.Shared



import android.content.SharedPreferences
import android.util.Log
import com.example.fromstarttofinish.usecases.model.FakeDataApiModel
import kotlin.random.Random

class SharedPreferenceUtil(val listToSave:List<FakeDataApiModel>,  val preference:SharedPreferences) {


    fun save(){
        Log.d("SharedPreferenceUtil", " save function")
        //see https://developer.android.com/reference/android/content/SharedPreferences.Editor#commit() please
        for (i in listToSave) {
            preference.edit().putInt(i.title, i.id).apply()
        }

    }

   fun testSharedPreferencesReading() {
       val random = Random.nextInt(0, 100)
       Log.d("MainViewModel", "random number gen : $random")
       if (listToSave.isNotEmpty()) {
           val randomFakeData = listToSave[random - 1]
           Log.d("SharedPreferenceUtil", "Random FakeDataItem: ${randomFakeData.toString()}")

       }
   }
}