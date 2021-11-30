package by.bsu.task6.entity.tunnel;

public enum PassingTime {
    LOW(3000),
    MEDIUM(5000),
    HIGH(10000);

    long millis;

    PassingTime(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return millis;
    }
}
