package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
    private static final int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        if (ticketPrice < 0) {
            throw new IllegalArgumentException("Negative ticket price provided");
        }
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice(Showing showing) {
        return calculateTicketPrice(showing);
    }

    private double getSequenceDiscount(int showSequence) {
        if (showSequence == 1) {
            return 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            return 2; // $2 discount for 2nd show
        }
        else if (showSequence == 7) {
            return 1; // $1 discount for 2nd show
        }
        return 0;
    }

    private double calculateTicketPrice(Showing showing) {
        double discount = Math.max(Math.max(getSpecialDiscount(), getSequenceDiscount(showing.getSequenceOfTheDay())), getTimeDiscount(showing));
        return ticketPrice - discount;
    }

    private double getSpecialDiscount() {
        if (specialCode == MOVIE_CODE_SPECIAL) {
            return ticketPrice * 0.2; // 20% discount for special movie
        }
        return 0;
    }

    private double getTimeDiscount(Showing showing) {
        LocalTime time = showing.getStartTime().toLocalTime();
        if (time.isAfter(LocalTime.of(10, 59)) && time.isBefore(LocalTime.of(16, 1))) {
            return ticketPrice * 0.25; // 25% discount for shows between 11am and 4pm
        }
        return 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}