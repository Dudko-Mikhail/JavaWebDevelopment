package by.bsu.task6.entity.train;

public enum Direction {
    FORWARD,
    REVERSE;

    public Direction getOppositeDirection() {
        return this == FORWARD ? REVERSE : FORWARD;
    }
}
