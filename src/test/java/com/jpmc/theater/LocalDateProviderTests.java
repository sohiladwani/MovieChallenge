package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        LocalDateProvider dateProvider = LocalDateProvider.getInstance();
        LocalDate currentDate = dateProvider.currentDate();
        assertEquals(2023, currentDate.getYear());
        assertEquals(5, currentDate.getMonthValue());
        assertEquals(8, currentDate.getDayOfMonth());
    }
}
