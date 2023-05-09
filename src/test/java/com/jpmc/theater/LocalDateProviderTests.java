package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTests {
    //Make sure current time is working correctly
    @Test
    void makeSureCurrentTime() {
        LocalDateProvider provider = LocalDateProvider.getInstance();
        LocalDate todaysdate = provider.currentDate();
        assertEquals(LocalDate.now(), todaysdate);
    }

    //Make sure the same instance is being returned everytime
    @Test
    void sameInstanceReturned() {
        LocalDateProvider dateProvider1 = LocalDateProvider.getInstance();
        LocalDateProvider dateProvider2 = LocalDateProvider.getInstance();
        assertEquals(dateProvider1, dateProvider2);
    }
}
