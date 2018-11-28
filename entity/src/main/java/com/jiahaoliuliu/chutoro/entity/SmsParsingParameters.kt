package com.jiahaoliuliu.chutoro.entity

data class SmsParsingParameters(val pattern: String, val dateFormat: String,
                                val positionQuantity: Int, val positionDestination: Int,
                                val positionDate: Int, val source: String)