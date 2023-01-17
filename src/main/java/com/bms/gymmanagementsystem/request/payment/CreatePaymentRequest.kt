package com.bms.gymmanagementsystem.request.payment

data class CreatePaymentRequest(
    val amount: Double,
    val clientId: String
)
