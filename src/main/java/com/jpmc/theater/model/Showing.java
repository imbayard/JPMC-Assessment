package com.jpmc.theater.model;

import java.time.LocalDateTime;

public class Showing {
    private final Movie movie;
    private final int movieId;
    private final LocalDateTime showStartTime;

    private final int MOVIE_CODE_SPECIAL = 1;
    private final int MOVIE_DISCOUNT_RANGE_START_HOUR = 11;
    private final int MOVIE_DISCOUNT_RANGE_END_HOUR = 16;
    private final int MOVIE_DISCOUNT_SPECIAL_DAY = 7;

    public Showing(Movie movie, int movieId, LocalDateTime showStartTime) {
        this.movie = movie;
        this.movieId = movieId;
        this.showStartTime = showStartTime;
    }

    public double calculateTicketPrice() {
        return movie.getTicketPrice() - getDiscount();
    }

    /**
     * The getDiscount method will calculate the maximum possible discount for this showing
     * <p>
     * Discounts Available:
     * 20% discount if specialCode is set to the special code
     * $3 discount for the first showing of a day
     * $2 showing for the second showing of a day
     * 25% discount if showing lands between the special time range
     * $1 discount if the showing is on the special day of the month
     *
     * @return the value to be subtracted from the ticket price
     */
    private double getDiscount() {
        // Calculate the discount for the special movie
        double specialMovieDiscount = 0;
        double ticketPrice = movie.getTicketPrice();
        if (MOVIE_CODE_SPECIAL == movie.getCode()) {
            specialMovieDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        // Calculate the discount for the sequence number
        double sequenceDiscount = 0;
        if (movieId == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (movieId == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }

        // Calculate the discount for the special timeframe
        double timeframeDiscount = 0;
        int hour = showStartTime.getHour();
        int min = showStartTime.getMinute();
        if ((hour >= MOVIE_DISCOUNT_RANGE_START_HOUR && hour < MOVIE_DISCOUNT_RANGE_END_HOUR) || (hour == MOVIE_DISCOUNT_RANGE_END_HOUR && min == 0)) {
            timeframeDiscount = ticketPrice * 0.25;
        }

        // Calculate the discount for the date
        double dateDiscount = 0;
        int day = showStartTime.getDayOfMonth();
        if (day == MOVIE_DISCOUNT_SPECIAL_DAY) {
            dateDiscount = 1;
        }

        // biggest discount wins
        double specialIdOrSequenceDiscount = Math.max(specialMovieDiscount, sequenceDiscount);
        double dateOrTimeDiscount = Math.max(timeframeDiscount, dateDiscount);
        return Math.max(specialIdOrSequenceDiscount, dateOrTimeDiscount);
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.movieId == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getMovieId() {
        return movieId;
    }

}
