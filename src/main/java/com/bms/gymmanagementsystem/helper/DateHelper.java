package com.bms.gymmanagementsystem.helper;

import java.time.LocalDateTime;

public class DateHelper {
    private DateHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }
}
