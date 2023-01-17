package com.bms.gymmanagementsystem.request.schedule

import java.time.LocalDateTime

data class CreateScheduleRequest (
    val session:String,

    val activity:String,

    val startTime: LocalDateTime,

    val endTime: LocalDateTime,

    val clientId:String,

    val trainerId:String
)