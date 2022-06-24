package com.jpmc.theater;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ShowingTests {

    @Test
    public void testDiscountIs7thDayDiscount() {
        Showing showing1 = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 12), 4, LocalDateTime.of(2022, 12, 7, 23, 0));
        assertEquals(11.5, showing1.calculateTicketPrice());
    }

    @Test
    public void testDiscountIsTimeRangeDiscount() {
        Showing showing1 = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 12), 4, LocalDateTime.of(2022, 12, 7, 12, 0));
        assertEquals(12.5 - (12.5 * .25), showing1.calculateTicketPrice());
    }

    @Test
    public void testDiscountIsSpecialCodeDiscount() {
        Showing showing1 = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 4, LocalDateTime.of(2022, 2, 7, 2, 0));
        assertEquals(12.5 - (12.5 * .2), showing1.calculateTicketPrice());
    }

    @Test
    public void testDiscountIsFirstShowingDiscount() {
        Showing showing1 = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 1, LocalDateTime.of(2022, 2, 7, 2, 0));
        assertEquals(9.5, showing1.calculateTicketPrice());
    }

    @Test
    public void testDiscountIsSecondShowingDiscount() {
        Showing showing1 = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0), 2, LocalDateTime.of(2022, 2, 7, 2, 0));
        assertEquals(10.5, showing1.calculateTicketPrice());
    }
}
