package by.bsu.task6.parser;

import by.bsu.task6.entity.train.Train;
import by.bsu.task6.entity.tunnel.Tunnel;

import java.util.List;

public interface CustomParser {
    List<Train> parseTrains(List<String> lines);

    List<Tunnel> parseTunnels(List<String> lines);
}
