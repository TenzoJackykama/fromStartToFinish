package com.example.fromstarttofinish.networking.dto

data class FakeDataItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)
// it shuld use this for mapping and separate networking data
//private fun FakeDataItem.getFakeDataItem() = FakeDataItem(this.title)