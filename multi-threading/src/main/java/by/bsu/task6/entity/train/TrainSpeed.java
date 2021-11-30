package by.bsu.task6.entity.train;

public enum TrainSpeed {
    LOW(9000),
    MEDIUM(6000),
    HIGH(4000);

    private long millis;

    TrainSpeed(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return millis;
    }
}
