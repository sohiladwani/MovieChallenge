package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Bulk of testing for discount differences is in movie tests class
public class ReservationTests {
    LocalDateProvider provider = LocalDateProvider.getInstance();
    @Test
    //Simple test to check total fee given movie is on special for 20 percent off and also its the first of the day
    //The first of the day discount should be higher then the special discount given the ticket price
    void testTotalFee() {
        Customer customer = new Customer("John Doe", "unused-id");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(5, 0))
        );
        assertTrue(new Reservation(customer, showing, 3).getTotalFee() == 28.5);
    }

    //Make sure the multiplier is working correctly for different audience headcounts
    @Test
    void testTotalFee2People() {
        Customer customer = new Customer("John Doe", "unused-id");
        Customer customer2 = new Customer("Jim Doe", "unused-id2");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                3,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(5, 0))
        );
        assertTrue(new Reservation(customer, showing, 3).getTotalFee() == 30);
        assertTrue(new Reservation(customer2, showing, 4).getTotalFee() == 40);
    }

    //Test a positive amount of audience count has been given
    @Test
    void testAudienceCount() {
        Customer customer = new Customer("John Doe", "unused-id");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(5, 0))
        );
        assertThrows(IllegalArgumentException.class, () -> new Reservation(customer, showing, -1));
    }


}
