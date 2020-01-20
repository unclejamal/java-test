package shop.time;

import java.time.LocalDate;

public class DateRange {

    private final LocalDate fromInclusive;
    private final LocalDate toInclusive;

    public DateRange(LocalDate fromInclusive, LocalDate toInclusive) {
        this.fromInclusive = fromInclusive;
        this.toInclusive = toInclusive;
    }

    public boolean includes(LocalDate date) {
        return date.isEqual(fromInclusive) || date.isEqual(toInclusive)
                || (date.isAfter(fromInclusive) && date.isBefore(toInclusive));
    }
}
