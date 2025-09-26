package com.parking_slot.Parking_Slot_valcare.util;
import java.time.Duration;
import java.time.OffsetDateTime;

public class FeeCalculator {
    public static long calculateHours(OffsetDateTime start, OffsetDateTime end) {
        long minutes = Duration.between(start, end).toMinutes();
        return (minutes + 59) / 60; 
    }

    public static double calculateTotal(long hours, double ratePerHour) {
        return hours * ratePerHour;
    }
}
