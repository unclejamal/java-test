package shop.time;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DateRangeTest {

    private final DateRange dateRange = new DateRange(
            LocalDate.of(2020, 1, 20),
            LocalDate.of(2020, 1, 22)
    );

    @Test
    public void containsOpeningDate() {
        assertThat(dateRange.includes(LocalDate.of(2020, 1, 20)), is(true));
    }

    @Test
    public void containsSomeDate() {
        assertThat(dateRange.includes(LocalDate.of(2020, 1, 21)), is(true));
    }

    @Test
    public void containsClosingDate() {
        assertThat(dateRange.includes(LocalDate.of(2020, 1, 22)), is(true));
    }

    @Test
    public void doesNotContainDatesOutside() {
        assertThat(dateRange.includes(LocalDate.of(2020, 1, 30)), is(false));
    }
}