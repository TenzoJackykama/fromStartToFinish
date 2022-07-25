package com.example.fromstarttofinish.networking.dto

import com.example.fromstarttofinish.usecases.model.footballApiModel

data class countriDto(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)

fun countriDto.getCountryDto(): footballApiModel = footballApiModel(this.parameters)