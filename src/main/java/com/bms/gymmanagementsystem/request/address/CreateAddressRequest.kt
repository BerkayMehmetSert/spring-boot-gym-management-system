package com.bms.gymmanagementsystem.request.address

data class CreateAddressRequest(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String
)