package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {
    LocalDateProvider provider = LocalDateProvider.getInstance();

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(5, 0))
        );
        assertTrue(new Reservation(customer, showing, 3).getTotalFee() == 28.5);
    }

    @Test
    void totalFee2People() {
        var customer = new Customer("John Doe", "unused-id");
        var customer2 = new Customer("Jim Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                3,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(5, 0))
        );
        assertTrue(new Reservation(customer, showing, 3).getTotalFee() == 30);
        assertTrue(new Reservation(customer2, showing, 4).getTotalFee() == 40);
    }

}
