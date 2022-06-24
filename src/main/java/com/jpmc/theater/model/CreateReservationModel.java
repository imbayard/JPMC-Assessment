package com.jpmc.theater.model;

import com.jpmc.theater.validation.CreateReservationModelValidation;

import java.util.Objects;

// This is the request model for the create reservation endpoint
@CreateReservationModelValidation()
public class CreateReservationModel {

    private String name;
    private Integer movieId;
    private Integer numberOfTickets;

    public CreateReservationModel(String name, Integer movieId, Integer numberOfTickets) {
        this.name = name;
        this.movieId = movieId;
        this.numberOfTickets = numberOfTickets;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public Integer getMovieId() {
        return movieId;
    }

    @Override
    public String toString() {
        return "CreateReservationModel{" +
                "name='" + name + '\'' +
                ", movieId=" + movieId +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReservationModel that = (CreateReservationModel) o;
        return Objects.equals(name, that.name) && Objects.equals(movieId, that.movieId) && Objects.equals(numberOfTickets, that.numberOfTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, movieId, numberOfTickets);
    }
}
