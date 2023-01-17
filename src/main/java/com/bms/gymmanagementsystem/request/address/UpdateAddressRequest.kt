package com.bms.gymmanagementsystem.request.address

data class UpdateAddressRequest(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String
)