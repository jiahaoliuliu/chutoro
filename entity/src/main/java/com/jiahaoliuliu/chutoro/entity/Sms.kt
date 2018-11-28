package com.jiahaoliuliu.chutoro.entity

data class Sms (val body: String, val date: Long) {
    companion object {
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_DATE = "date"
        const val COLUMN_TYPE = "type"
        const val COLUMN_BODY = "body"
    }
}