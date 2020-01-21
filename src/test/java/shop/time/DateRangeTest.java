package shop.time;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static shop.time.DateRange.dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth;
import static shop.time.DateRange.dateRangeFromYesterdayAndValidForSevenDays;

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

    @Test
    public void dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth_startingInTodaysMonth() {
        LocalDate today = LocalDate.of(2020, 1, 20);

        DateRange actual = dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth(today);

        DateRange expectedDateRange = new DateRange(
                LocalDate.of(2020, 1, 23),
                LocalDate.of(2020, 2, 29)
        );
        assertThat(actual, Matchers.equalTo(expectedDateRange));
    }

    @Test
    public void dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth_slippingIntoNextMonth() {
        LocalDate today = LocalDate.of(2020, 1, 31);

        DateRange actual = dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth(today);

        DateRange expectedDateRange = new DateRange(
                LocalDate.of(2020, 2, 3),
                LocalDate.of(2020, 2, 29)
        );
        assertThat(actual, Matchers.equalTo(expectedDateRange));
    }

    @Test
    public void dateRangeFromYesterdayAndValidForNumberOfDays_startingInTodaysMonth() {
        LocalDate today = LocalDate.of(2020, 1, 20);

        DateRange actual = dateRangeFromYesterdayAndValidForSevenDays(today);

        DateRange expectedDateRange = new DateRange(
                LocalDate.of(2020, 1, 19),
                LocalDate.of(2020, 1, 25)
        );
        assertThat(actual, Matchers.equalTo(expectedDateRange));
    }

    @Test
    public void dateRangeFromYesterdayAndValidForNumberOfDays_slippingIntoPreviousMonth() {
        LocalDate today = LocalDate.of(2020, 1, 1);

        DateRange actual = dateRangeFromYesterdayAndValidForSevenDays(today);

        DateRange expectedDateRange = new DateRange(
                LocalDate.of(2019, 12, 31),
                LocalDate.of(2020, 1, 6)
        );
        assertThat(actual, Matchers.equalTo(expectedDateRange));
    }

}