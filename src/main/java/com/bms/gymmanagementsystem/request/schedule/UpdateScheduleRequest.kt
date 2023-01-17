package com.bms.gymmanagementsystem.request.schedule

import java.time.LocalDateTime

data class UpdateScheduleRequest (
    val session:String,

    val activity:String
)