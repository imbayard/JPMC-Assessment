package com.jpmc.theater.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jpmc.theater.service.MovieTheaterGenericUtil;

import java.time.Duration;
import java.util.Objects;

public class Movie {

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int getCode() {
        return specialCode;
    }

    public String getTitle() {
        return title;
    }

    public String getRunningTime() {
        return MovieTheaterGenericUtil.humanReadableFormat(runningTime);
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0 && specialCode == movie.specialCode && Objects.equals(title, movie.title) && Objects.equals(runningTime, movie.runningTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", runningTime=" + runningTime +
                ", ticketPrice=" + ticketPrice +
                ", specialCode=" + specialCode +
                '}';
    }
}