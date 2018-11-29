package com.jiahaoliuliu.chutoro.usecase.mapsmsusecase

data class SmsParsingParameters(val pattern: String, val dateFormat: String,
                                val positionQuantity: Int, val positionDestination: Int,
                                val positionDate: Int, val source: String)