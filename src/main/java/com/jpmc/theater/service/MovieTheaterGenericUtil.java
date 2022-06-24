package com.jpmc.theater.service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MovieTheaterGenericUtil {

    /**
     * This method takes a Duration and turns it into a human-readable String: "(X hour Y minutes)"
     *
     * @param duration the duration to transform into human-readable string
     * @return The human-readable string
     */
    public static String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private static String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "s";
        }
    }
}
