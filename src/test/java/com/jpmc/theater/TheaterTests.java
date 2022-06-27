package com.jpmc.theater;

import com.jpmc.theater.model.LocalDateProvider;
import com.jpmc.theater.model.ScheduledMovieModel;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.util.MovieTheaterUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class TheaterTests {

    @Test
    public void testTheaterToSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());

        Showing showing = theater.getSchedule().get(8);
        ScheduledMovieModel movie = new ScheduledMovieModel(9, showing.getStartTime(), "The Batman", "(1 hour 35 minutes)", "$9.0");
        ScheduledMovieModel actual = MovieTheaterUtil.toScheduleModel(theater.getSchedule()).get(8);

        assertEquals(movie, actual);
    }


}
