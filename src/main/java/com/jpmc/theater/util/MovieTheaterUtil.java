package com.jpmc.theater.util;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.ScheduledMovieModel;
import com.jpmc.theater.model.Showing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieTheaterUtil {

    private static final Logger logger = LoggerFactory.getLogger(MovieTheaterUtil.class);
    private static final int MOVIE_CODE_SPECIAL = 1;
    private static final int MOVIE_DISCOUNT_RANGE_START_HOUR = 11;
    private static final int MOVIE_DISCOUNT_RANGE_END_HOUR = 16;
    private static final int MOVIE_DISCOUNT_SPECIAL_DAY = 7;

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
     * @param showing the showing to calculate the discount for
     * @return the value to be subtracted from the ticket price
     */
    public static Double getDiscount(Showing showing) {
        LocalDateTime showStartTime = showing.getStartTime();
        Movie movie = showing.getMovie();
        int movieId = showing.getMovieId();

        double movieDiscount = 0;
        double ticketPrice = movie.getTicketPrice();

        int hour = showStartTime.getHour();
        int min = showStartTime.getMinute();
        if ((hour >= MOVIE_DISCOUNT_RANGE_START_HOUR && hour < MOVIE_DISCOUNT_RANGE_END_HOUR) || (hour == MOVIE_DISCOUNT_RANGE_END_HOUR && min == 0)) {
            movieDiscount = ticketPrice * 0.25;
        } else if (MOVIE_CODE_SPECIAL == movie.getCode()) {
            movieDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        int day = showStartTime.getDayOfMonth();
        if (movieId == 1) {
            movieDiscount = Math.max(3, movieDiscount); // $3 discount for 1st show
        } else if (movieId == 2) {
            movieDiscount = Math.max(2, movieDiscount); // $2 discount for 2nd show
        } else if (day == MOVIE_DISCOUNT_SPECIAL_DAY) {
            movieDiscount = Math.max(1, movieDiscount);
        }

        return movieDiscount;
    }

    /**
     * The toScheduleModel will convert the schedule to its POJO format
     *
     * @return a List of MovieDetailsModels that describe the schedule for the day
     */
    public static List<ScheduledMovieModel> toScheduleModel(List<Showing> schedule) {
        List<ScheduledMovieModel> outputSchedule = new ArrayList<>();
        schedule.forEach(showing -> {
            Movie movie = showing.getMovie();
            String fee = "$" + Double.toString(showing.getMovieFee());
            ScheduledMovieModel movieDetails = new ScheduledMovieModel(
                    showing.getMovieId(),
                    showing.getStartTime(),
                    movie.getTitle(),
                    movie.getRunningTime(),
                    fee
            );
            outputSchedule.add(movieDetails);
        });
        logger.info("Printed Schedule: {}", outputSchedule);
        return outputSchedule;
    }
}
