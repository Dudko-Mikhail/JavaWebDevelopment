package by.bsu.task6.entity.train;

public enum TrainState {
    BEFORE_TUNNEL,
    FIRST_TUNNEL,
    BETWEEN_TUNNELS,
    SECOND_TUNNEL,
    END;

    public TrainState toNextState() {
        if (this == END) {
            return END;
        }
        return TrainState.values()[this.ordinal() + 1];
    }
}
