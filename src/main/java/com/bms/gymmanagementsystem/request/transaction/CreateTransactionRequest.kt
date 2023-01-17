package com.bms.gymmanagementsystem.request.transaction

data class CreateTransactionRequest(
    val name: String,
    val clientId:String
)