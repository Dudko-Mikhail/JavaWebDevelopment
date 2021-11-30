package by.bsu.task6.creator;

import by.bsu.task6.entity.train.Direction;
import by.bsu.task6.entity.train.Train;
import by.bsu.task6.entity.train.TrainSpeed;
import by.bsu.task6.entity.train.TrainType;

import java.util.Random;

public class TrainGenerator {
    private static TrainGenerator instance;
    private final Random random;

    public static TrainGenerator getInstance() {
        if (instance == null) {
            instance = new TrainGenerator();
        }
        return instance;
    }

    public Train getRandomTrain() {
        Direction[] directions = Direction.values();
        Direction direction = directions[random.nextInt(directions.length)];
        TrainSpeed[] trainSpeeds = TrainSpeed.values();
        TrainSpeed speed = trainSpeeds[random.nextInt(trainSpeeds.length)];
        TrainType[] trainTypes = TrainType.values();
        TrainType type = trainTypes[random.nextInt(trainTypes.length)];
        long beforeFirstTunnelSleepTime = random.nextLong(speed.getMillis(), speed.getMillis() * 5);
        return new Train(type, speed, direction, beforeFirstTunnelSleepTime);
    }

    private TrainGenerator () {
        random = new Random();
    }
}
