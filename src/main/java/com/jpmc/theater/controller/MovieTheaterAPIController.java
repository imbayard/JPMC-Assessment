package com.jpmc.theater.controller;

import com.jpmc.theater.model.*;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.util.MovieTheaterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/movie-theater")
public class MovieTheaterAPIController {

    @Autowired
    ReservationService reservationService;

    private final Logger logger = LoggerFactory.getLogger(MovieTheaterAPIController.class);
    private final String baseEndpoint = "/v1/movie-theater";

    /**
     * @return This method will return the showings for the day today
     */
    @GetMapping(value = "/get-movie-list", produces = {"application/json"})
    public ResponseEntity<List<ScheduledMovieModel>> getMovieList() {
        logger.info("Endpoint {}/get-movie-list hit successfully.", baseEndpoint);
        Theater theater = new Theater(LocalDateProvider.singleton());

        return ResponseEntity.ok(MovieTheaterUtil.toScheduleModel(theater.getSchedule()));
    }

    /**
     * This method will create a reservation based on the input model
     *
     * @param reservationToCreate the reservation to create
     * @return the created reservation
     */
    @PostMapping(value = "/create-reservation", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ReservationModel> createReservation(@RequestBody @Valid CreateReservationModel reservationToCreate) {
        logger.info("Endpoint {}/create-reservation hit successfully.", baseEndpoint);
        Theater theater = new Theater(LocalDateProvider.singleton());

        ReservationModel createdReservation = reservationService.createReservation(reservationToCreate, theater.getSchedule());
        logger.info("Reservation created: {}", createdReservation);
        return ResponseEntity.ok(createdReservation);
    }
}
