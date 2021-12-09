package by.bsu.task6.entity.train;

public enum TrainSpeed {
    LOW(6000),
    MEDIUM(4000),
    HIGH(2000);

    private long millis;

    TrainSpeed(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return millis;
    }
}
