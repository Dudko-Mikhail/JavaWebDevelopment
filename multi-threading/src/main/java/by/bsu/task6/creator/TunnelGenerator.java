package by.bsu.task6.creator;

import by.bsu.task6.entity.tunnel.PassingTime;
import by.bsu.task6.entity.tunnel.Tunnel;
import com.github.javafaker.Faker;

import java.util.Random;

public class TunnelGenerator {
    private static TunnelGenerator instance;
    private final Random random;

    public static TunnelGenerator getInstance() {
        if (instance == null) {
            instance = new TunnelGenerator();
        }
        return instance;
    }

    public Tunnel getRandomTunnel() {
        PassingTime[] passingTimes = PassingTime.values();
        PassingTime time = passingTimes[random.nextInt(passingTimes.length)];
        int trainsInOneDirectionLimit = random.nextInt(2, 4);
        int oneDirectionSeriesLimit = random.nextInt(1, 4);
        Faker faker = Faker.instance();
        String tunnelName = faker.app().name();
        return new Tunnel(trainsInOneDirectionLimit, time, oneDirectionSeriesLimit, tunnelName);
    }

    private TunnelGenerator () {
        random = new Random();
    }
}
