package by.bsu.task6.parser.impl;

import by.bsu.task6.entity.train.Direction;
import by.bsu.task6.entity.train.Train;
import by.bsu.task6.entity.train.TrainSpeed;
import by.bsu.task6.entity.train.TrainType;
import by.bsu.task6.entity.tunnel.PassingTime;
import by.bsu.task6.entity.tunnel.Tunnel;
import by.bsu.task6.parser.CustomParser;

import java.util.List;

public class CustomParserImpl implements CustomParser {
    @Override
    public List<Train> parseTrains(List<String> lines) {
        return lines.stream()
                    .map(line -> {
                        String[] data = line.split("\\s");
                        TrainType type = TrainType.valueOf(data[0].toUpperCase());
                        TrainSpeed speed = TrainSpeed.valueOf(data[1].toUpperCase());
                        Direction direction = Direction.valueOf(data[2].toUpperCase());
                        long timeBeforeTunnel = Long.parseLong(data[3]);
                        return new Train(type, speed, direction, timeBeforeTunnel);
                    })
                    .toList();
    }

    @Override
    public List<Tunnel> parseTunnels(List<String> lines) {
        return lines.stream()
                .map(line -> {
                    String[] data = line.split("\\s");
                    int trainsInOneDirectionLimit = Integer.parseInt(data[0]);
                    PassingTime passingTime = PassingTime.valueOf(data[1].toUpperCase());
                    int oneDirectionSeriesLimit = Integer.parseInt(data[2]);
                    String tunnelName = data[3];
                    return new Tunnel(trainsInOneDirectionLimit, passingTime, oneDirectionSeriesLimit, tunnelName);
                })
                .toList();
    }
}
