package com.jpmc.theater.model;

import com.jpmc.theater.util.MovieTheaterUtil;

import java.time.LocalDateTime;

public class Showing {
    private final Movie movie;
    private final int movieId;
    private final LocalDateTime showStartTime;

    public Showing(Movie movie, int movieId, LocalDateTime showStartTime) {
        this.movie = movie;
        this.movieId = movieId;
        this.showStartTime = showStartTime;
    }

    public double calculateTicketPrice() {
        return movie.getTicketPrice() - MovieTheaterUtil.getDiscount(this);
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getMovieId() {
        return movieId;
    }

}
