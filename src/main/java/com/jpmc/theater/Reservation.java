package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        if (audienceCount <= 0) {
            throw new IllegalArgumentException("Invalid audience count provided");
        }
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public double getTotalFee() {
        return showing.getMovieFee() * audienceCount;
    }
}