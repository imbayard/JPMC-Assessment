package com.jpmc.theater.model;

import java.util.Objects;

public class ReservationModel {
    private final Customer customer;
    private final Showing showing;
    private final int audienceCount;
    private final Double reservationFee;

    public ReservationModel(Customer customer, Showing showing, int audienceCount, Double reservationFee) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
        this.reservationFee = reservationFee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public Double getReservationFee() {
        return reservationFee;
    }

    public Showing getShowing() {
        return showing;
    }

    @Override
    public String toString() {
        return "ReservationModel{" +
                "customer=" + customer +
                ", showing=" + showing +
                ", audienceCount=" + audienceCount +
                ", reservationFee=" + reservationFee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationModel that = (ReservationModel) o;
        return audienceCount == that.audienceCount && Objects.equals(customer, that.customer) && Objects.equals(showing, that.showing) && Objects.equals(reservationFee, that.reservationFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, showing, audienceCount, reservationFee);
    }
}