package ir.asra.parking.myutility;


import ir.asra.parking.model.Parking;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MyUtility {
    public static String TIMEFORMAT = "yyyy-MM-dd HH:mm";
    public static DateTimeFormatter MYFORMATTER = DateTimeFormatter.ofPattern(TIMEFORMAT)
            .withZone(ZoneId.systemDefault());


    public static boolean checkPlate(String plate) {

        if (plate.length() != 8)
            return false;
        char plateChar = plate.charAt(2);
        for (char i = 'a'; i <= 'z'; i++) {
            if (i == plateChar)
                return true;
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            if (i == plateChar)
                return true;
        }
        return false;
    }


    public static long calculateExpense(Parking parking) {
        LocalDateTime arrivalTime = LocalDateTime.parse(parking.getArrivalTime(), DateTimeFormatter.ofPattern(TIMEFORMAT));
        LocalDateTime departureTime = LocalDateTime.parse(MYFORMATTER.format(Instant.now()), DateTimeFormatter.ofPattern(TIMEFORMAT));
        long totalMinutes = Duration.between(arrivalTime, departureTime).toMinutes();
        return totalMinutes;
    }


}
