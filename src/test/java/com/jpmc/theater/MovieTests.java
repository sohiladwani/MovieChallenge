package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(10, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    @Test
    void specialMovieAndSeqDay1LowPrice() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(60),8, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(5, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    @Test
    void specialMovieAndSeqDay1HighPrice() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(200),57, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(45.6, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    @Test
    void normalMovieDay2() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(45),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(10.5, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }

    @Test
    void normalMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(91),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(12.5, spiderMan.getTicketPrice(showing));

        System.out.println(spiderMan.getRunningTime());
    }
    @Test
    void normalMovieDay7() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(8, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }
    @Test
    void discountelevenAmGreater() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        assertEquals(6.75, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }
    @Test
    void discountelevenAmGreater2() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
        assertEquals(6.75, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }
    @Test
    void discountelevenAmGreater3() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)));
        assertEquals(6.75, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }
    @Test
    void discountelevenAmLess() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 5, 0);
        Showing showing = new Showing(theBatMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
        assertEquals(2, theBatMan.getTicketPrice(showing));

        System.out.println(theBatMan.getRunningTime());
    }
}
