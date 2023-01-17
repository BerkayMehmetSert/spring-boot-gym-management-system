package com.bms.gymmanagementsystem.request.client

import com.bms.gymmanagementsystem.request.address.UpdateAddressRequest

data class UpdateClientRequest(
    val firstName: String,

    val lastName: String,

    val age: Int,

    val email: String,

    val phoneNumber: String,

    val password: String
)
