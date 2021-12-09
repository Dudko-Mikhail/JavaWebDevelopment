package by.bsu.task6.entity.tunnel;

public enum PassingTime {
    LOW(6000),
    MEDIUM(8000),
    HIGH(10000);

    long millis;

    PassingTime(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return millis;
    }
}
