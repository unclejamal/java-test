package shop.time;

import java.time.LocalDate;

public class FrozenClock implements Clock {

    private LocalDate today;

    @Override
    public LocalDate today() {
        return today;
    }

    public void setTodayTo(LocalDate today) {
        this.today = today;
    }
}
