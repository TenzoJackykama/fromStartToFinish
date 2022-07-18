package com.example.fromstarttofinish.networking.dto

import com.example.fromstarttofinish.usecases.model.FakeDataApiModel

data class FakeDataItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)
//it shuld use this for mapping and separate networking data
fun FakeDataItem.getFakeDataItem(): FakeDataApiModel = FakeDataApiModel(this.title)