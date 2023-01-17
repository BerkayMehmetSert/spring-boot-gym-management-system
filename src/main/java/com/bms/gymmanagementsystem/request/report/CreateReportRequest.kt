package com.bms.gymmanagementsystem.request.report

data class CreateReportRequest(
    val clientId: String,
    val transactionId: String
)