package chapter4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

public class Well {

    public static void main(String[] args) {
        System.out.println(Arrays.mismatch(new int[]{1}, new int[]{1, 2, 3})); // 1
        LocalDate date1 = LocalDate.of(2026, 4, 20);
        LocalTime time1 = LocalTime.of(10, 37);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        System.out.println(date1 + " " + time1 + " " + dateTime1);
    
        ZoneId zoneId = ZoneId.of("Asia/Dhaka");
        System.out.println(zoneId);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.toInstant());
        
        LocalDate date = LocalDate.of(2025, Month.NOVEMBER, 2);
        LocalTime time = LocalTime.of(1,  30);
        ZoneId zone1 = ZoneId.of("US/Eastern");
        ZonedDateTime exp = ZonedDateTime.of(date, time, zone1);
        System.out.println();
        System.out.println(exp);
        System.out.println(exp = exp.plusHours(1));
        System.out.println(exp = exp.plusHours(1));

        exp = ZonedDateTime.of(LocalDateTime.of(2025, Month.MARCH, 9, 2, 5), zone1);
        System.out.println(exp);
    }
}