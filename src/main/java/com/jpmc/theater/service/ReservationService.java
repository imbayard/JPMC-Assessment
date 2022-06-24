package com.jpmc.theater.service;

import com.jpmc.theater.model.CreateReservationModel;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.ReservationModel;
import com.jpmc.theater.model.Showing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The create reservation method. This is the main service method for the create reservation endpoint
     *
     * @param reservationToCreate The reservation that the user would like to create
     * @param showings            The list of showings for the day
     * @return The created reservation model
     */
    public ReservationModel createReservation(CreateReservationModel reservationToCreate, List<Showing> showings) {
        Showing showing;
        int movieId = reservationToCreate.getMovieId();
        try {
            showing = showings.get(movieId - 1);
            logger.debug("Creating reservation for showing: {}", showing);
        } catch (RuntimeException ex) {
            throw new IllegalStateException("Unable to find any showing for the given movie id" + Integer.toString(movieId));
        }
        int numTickets = reservationToCreate.getNumberOfTickets();

        Customer customer = new Customer(reservationToCreate.getName(), UUID.randomUUID().toString().substring(0, 8));
        return new ReservationModel(customer, showing, numTickets, calculateFeeForReservation(showing, numTickets));
    }

    /**
     * A method to calculate the reservation fee based on the showing and number of tickets
     *
     * @param showing    The showing to find the fee for
     * @param numTickets The number of tickets the user would like to purchase
     * @return A Double representing the total cost of the reservation
     */
    private Double calculateFeeForReservation(Showing showing, int numTickets) {
        return showing.calculateTicketPrice() * numTickets;
    }
}
