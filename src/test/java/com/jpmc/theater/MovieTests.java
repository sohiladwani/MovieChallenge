package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieTests {

    //Testing 20 percent discount
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(10, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    //Testing max comparison of day 1 discount and special code where Day 1 wins
    @Test
    void specialMovieAndSeqDay1LowPrice() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(60),8, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(5, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    //Testing max comparison of day 1 discount and special code where Special wins
    @Test
    void specialMovieAndSeqDay1HighPrice() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(200),57, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(45.6, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    //Testing if seq 2 discount works
    @Test
    void normalMovieDay2() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(45),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(10.5, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    //No discount
    @Test
    void normalMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(91),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(12.5, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    //Testing if the seq 7 discount works
    @Test
    void normalMovieDay7() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(8, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }

    //Checking is the 11am-4pm is better then the 7th seq discount EDGE case 11am
    @Test
    void discountElevenAmGreaterEdgeCase() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        assertEquals(6.75, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }

    //Checking is the 11am-4pm is better then the 7th seq discount EDGE case 4pm
    @Test
    void discountElevenAmGreater2EdgeCase() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
        assertEquals(6.75, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }

    //Checking is the 11am-4pm is better then the 7th seq discount
    @Test
    void discountElevenAmGreater3() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)));
        assertEquals(6.75, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }

    //Checking if 11 am-4pm disocunt is worst then the day 1 discount
    @Test
    void discountElevenAmLess() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 5, 0);
        Showing showing = new Showing(theBatMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
        assertEquals(2, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }

    //Testing a negative ticket price
    @Test
    void testNegativeTicketPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), -5, 1));
    }
}
