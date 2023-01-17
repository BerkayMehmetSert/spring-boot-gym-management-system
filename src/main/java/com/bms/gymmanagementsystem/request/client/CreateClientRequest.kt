package com.bms.gymmanagementsystem.request.client

import com.bms.gymmanagementsystem.model.Gender
import com.bms.gymmanagementsystem.request.address.CreateAddressRequest

data class CreateClientRequest(
    val firstName: String,

    val lastName: String,

    val age: Int,

    val gender: Gender,

    val address:CreateAddressRequest,

    val email: String,

    val phoneNumber: String,

    val password: String
)
