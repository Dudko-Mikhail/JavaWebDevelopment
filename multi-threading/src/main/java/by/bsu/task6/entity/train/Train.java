package by.bsu.task6.entity.train;

import by.bsu.task6.entity.tunnel.Tunnel;
import by.bsu.task6.entity.tunnel.TunnelHolder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Train extends Thread {
    private static final long WAITING_TIME_MILLIS = 1500;
    private static final Logger logger = LogManager.getLogger();
    private final TrainType type;
    private final TrainSpeed speed;
    private final long beforeFirstTunnelSleepTime;
    private Direction direction;
    private TrainState trainState;

    public Train(TrainType type, TrainSpeed speed, Direction direction, long beforeFirstTunnelSleepTime) {
        this.type = type;
        this.speed = speed;
        this.direction = direction;
        this.beforeFirstTunnelSleepTime = beforeFirstTunnelSleepTime;
        this.trainState = TrainState.BEFORE_TUNNEL;
    }

    public TrainType getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public TrainState getTrainState() {
        return trainState;
    }

    @Override
    public void run() {
        while (trainState != TrainState.END) {
            try {
                move();
            } catch (InterruptedException e) {
                logger.warn(e);
                Thread.currentThread().interrupt();
            }
        }
        logger.info(String.format("The train %d went through all the tunnels", getId()));
    }

    private void move() throws InterruptedException {
        logger.log(Level.INFO, String.format("Train %d. CurrentState: %s", getId(), trainState)); // FIXME: 24.11.2021
        switch (trainState) {
            case FIRST_TUNNEL, SECOND_TUNNEL -> {
                TunnelHolder holder = TunnelHolder.getInstance();
                Tunnel currentTunnel = holder.getTunnel(this);
                while (!currentTunnel.tryAcceptTrain(this)) {
                    TimeUnit.MILLISECONDS.sleep(WAITING_TIME_MILLIS);
                }
            }
            case BEFORE_TUNNEL -> TimeUnit.MILLISECONDS.sleep(beforeFirstTunnelSleepTime);
            default -> TimeUnit.MILLISECONDS.sleep(speed.getMillis());
        }
        if (trainState != TrainState.END) {
            trainState = trainState.toNextState();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Train{");
        sb.append("type=").append(type);
        sb.append(", speed=").append(speed);
        sb.append(", beforeFirstTunnelSleepTime=").append(beforeFirstTunnelSleepTime);
        sb.append(", direction=").append(direction);
        sb.append('}');
        return sb.toString();
    }
}
