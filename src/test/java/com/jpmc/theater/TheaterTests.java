package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.getInstance());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(37.5, reservation.getTotalFee()); // This Movie is on Special

        Customer bob = new Customer("Bob Doe", "id-123456");
        Reservation reservation2 = theater.reserve(bob, 1, 4);
        assertEquals(32, reservation2.getTotalFee()); // This movie is on day 1 discount

        Customer jim = new Customer("Jim Doe", "id-123457");
        Reservation reservation3 = theater.reserve(bob, 3, 1);
        assertEquals(6.75, reservation3.getTotalFee()); // This movie is on normal pricing
    }

    //I chose to print the movieSchedule with the discounts built in to incentivize  customers by them seeing the true rate
    //they will be paying
    @Test
    void printMovieSchedule() {
        LocalDateProvider datetime = new MockLocalDateProvider();
        Theater theater = new Theater(datetime);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        theater.printSchedule();
        String expectedOutput = datetime.currentDate() + "\n"
                + "===================================================\n"
                + "1: "+ datetime.currentDate() + "T09:00 Turning Red (1 hour 25 minutes) $8.0\n"
                + "2: "+ datetime.currentDate() + "T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $9.375\n"
                + "3: "+ datetime.currentDate() + "T12:50 The Batman (1 hour 35 minutes) $6.75\n"
                + "4: "+ datetime.currentDate() + "T14:30 Turning Red (1 hour 25 minutes) $8.25\n"
                + "5: "+ datetime.currentDate() + "T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $10.0\n"
                + "6: "+ datetime.currentDate() + "T17:50 The Batman (1 hour 35 minutes) $9.0\n"
                + "7: "+ datetime.currentDate() + "T19:30 Turning Red (1 hour 25 minutes) $10.0\n"
                + "8: "+ datetime.currentDate() + "T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $10.0\n"
                + "9: "+ datetime.currentDate() + "T23:00 The Batman (1 hour 35 minutes) $9.0\n"
                + "===================================================\n";

        //Had to do this because was having a failed unit test due to line separator errors
        String actualOut = outContent.toString().replaceAll("\n", "").replaceAll("\r", "");
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, actualOut);
    }

    // This is a mock implementation of LocalDateProvider used for testing purposes
    static class MockLocalDateProvider extends LocalDateProvider {
        @Override
        public LocalDate currentDate() {
            return LocalDate.now();
        }
    }
}
