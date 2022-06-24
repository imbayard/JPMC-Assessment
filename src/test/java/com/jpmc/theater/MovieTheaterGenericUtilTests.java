package com.jpmc.theater;

import com.jpmc.theater.service.MovieTheaterGenericUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class MovieTheaterGenericUtilTests {

    @Test
    public void testSuccessfulToHumanReadable() {
        Duration duration = Duration.ofMinutes(95);
        String expected = "(1 hour 35 minutes)";

        assertEquals(expected, MovieTheaterGenericUtil.humanReadableFormat(duration));
    }
}
