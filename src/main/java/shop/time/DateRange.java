package shop.time;

import shop.util.ValueObject;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class DateRange extends ValueObject {

    private final LocalDate fromInclusive;
    private final LocalDate toInclusive;

    public static DateRange dateRangeFromYesterdayAndValidForSevenDays(LocalDate today) {
        return new DateRange(today.minusDays(1), today.plusDays(5));
    }

    public static DateRange dateRangeFromIn3DaysAndValidUntilTheEndOfTheFollowingMonth(LocalDate today) {
        return new DateRange(today.plusDays(3), today.plusMonths(1).with(lastDayOfMonth()));
    }

    public DateRange(LocalDate fromInclusive, LocalDate toInclusive) {
        this.fromInclusive = fromInclusive;
        this.toInclusive = toInclusive;
    }

    public boolean includes(LocalDate date) {
        return date.isEqual(fromInclusive) || date.isEqual(toInclusive)
                || (date.isAfter(fromInclusive) && date.isBefore(toInclusive));
    }
}
