package com.example.fromstarttofinish.networking.dto

data class FakeDataItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

private fun FakeDataItem.getFakeDataItem(body : String) = FakeDataItem(this.title)