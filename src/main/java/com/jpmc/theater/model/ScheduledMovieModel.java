package com.jpmc.theater.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ScheduledMovieModel {

    private final int sequenceOfTheDay;
    private final LocalDateTime startTime;
    private final String movieTitle;
    private final String runningTime;
    private final String movieFee;

    public ScheduledMovieModel(int sequenceOfTheDay, LocalDateTime startTime, String movieTitle, String runningTime, String movieFee) {
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.startTime = startTime;
        this.movieTitle = movieTitle;
        this.runningTime = runningTime;
        this.movieFee = movieFee;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public String getMovieFee() {
        return movieFee;
    }

    @Override
    public String toString() {
        return "MovieDetailsForScheduleModel{" +
                "sequenceOfTheDay=" + sequenceOfTheDay +
                ", startTime=" + startTime +
                ", movieTitle='" + movieTitle + '\'' +
                ", runningTime='" + runningTime + '\'' +
                ", movieFee='" + movieFee + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledMovieModel that = (ScheduledMovieModel) o;
        return sequenceOfTheDay == that.sequenceOfTheDay && Objects.equals(startTime, that.startTime) && Objects.equals(movieTitle, that.movieTitle) && Objects.equals(runningTime, that.runningTime) && Objects.equals(movieFee, that.movieFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceOfTheDay, startTime, movieTitle, runningTime, movieFee);
    }
}
