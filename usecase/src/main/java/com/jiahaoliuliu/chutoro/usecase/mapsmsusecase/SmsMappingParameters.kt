package com.jiahaoliuliu.chutoro.usecase.mapsmsusecase

data class SmsMappingParameters(val pattern: String, val dateFormat: String,
                                val positionQuantity: Int, val positionDestination: Int,
                                val positionDate: Int, val source: String)