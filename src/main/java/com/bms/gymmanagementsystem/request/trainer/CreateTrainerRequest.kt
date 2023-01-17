package com.bms.gymmanagementsystem.request.trainer

import com.bms.gymmanagementsystem.model.Gender
import com.bms.gymmanagementsystem.request.address.CreateAddressRequest

data class CreateTrainerRequest(
    val firstName: String,

    val lastName: String,

    val gender: Gender,

    val salary: Double,

    val address: CreateAddressRequest
)