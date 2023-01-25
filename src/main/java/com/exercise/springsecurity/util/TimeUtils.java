package com.exercise.springsecurity.util;

import java.util.Date;

public class TimeUtils {
    public static Date getTimeAfterGivenMinutes(long minutes) {
        return new Date(System.currentTimeMillis() + (1000 * 60 * minutes));
    }
}
