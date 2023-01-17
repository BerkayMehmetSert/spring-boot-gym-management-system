package com.bms.gymmanagementsystem.request.membership

data class CreateMemberShipRequest(
    val status: String,
    val clientId: String
)