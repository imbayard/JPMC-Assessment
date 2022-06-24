package com.jpmc.theater;

import com.jpmc.theater.model.*;
import com.jpmc.theater.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class ReservationServiceTests {

    @InjectMocks
    ReservationService service;

    @Test
    public void testSuccessfulReservationCreation() {
        CreateReservationModel reservationToCreate = new CreateReservationModel("Bayard Eton", 1, 1);
        Showing showing1 = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 1, LocalDateTime.now());
        List<Showing> showings = List.of(showing1);

        ReservationModel expected = new ReservationModel(new Customer("Bayard Eton", "abc"), showing1, 1, 12.5);
        ReservationModel actual = service.createReservation(reservationToCreate, showings);

        assertEquals(expected.getCustomer().getName(), actual.getCustomer().getName());
        assertEquals(expected.getShowing().getMovie().getTitle(), actual.getShowing().getMovie().getTitle());
    }

    @Test
    public void testFailureNoMovieFound() {
        CreateReservationModel reservationToCreate = new CreateReservationModel("Bayard Eton", 4, 1);
        Showing showing1 = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 1, LocalDateTime.now());
        List<Showing> showings = List.of(showing1);

        RuntimeException ex = null;
        try {
            service.createReservation(reservationToCreate, showings);
        } catch (RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
    }
}
