package shop.time;

import java.time.LocalDate;

public class SystemClock implements Clock {
    @Override
    public LocalDate today() {
        return LocalDate.now();
    }
}
