package by.bsu.task6.entity.tunnel;

import by.bsu.task6.entity.train.Direction;
import by.bsu.task6.entity.train.Train;
import by.bsu.task6.exception.ReaderException;
import by.bsu.task6.exception.TunnelException;
import by.bsu.task6.parser.CustomParser;
import by.bsu.task6.parser.impl.CustomParserImpl;
import by.bsu.task6.reader.CustomFileReader;
import by.bsu.task6.reader.impl.CustomFileReaderImpl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class TunnelHolder {
    private static final int MAX_TUNNEL_AMOUNT = 2;
    private static final AtomicBoolean isCreated;
    private static final ReentrantLock lock;
    private static TunnelHolder instance;
    private final Tunnel[] tunnels;

    static  {
        lock = new ReentrantLock();
        isCreated = new AtomicBoolean(false);
    }

    public static TunnelHolder getInstance() {
        if (!isCreated.get()) {
            try {
                lock.lock();
                if (!isCreated.get()) {
                    instance = new TunnelHolder();
                    isCreated.set(true);
                }
            }
            finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Tunnel getTunnel(Train train) {
        return switch (train.getTrainState()) {
            case FIRST_TUNNEL -> train.getDirection() == Direction.FORWARD ? tunnels[0] : tunnels[1];
            case SECOND_TUNNEL -> train.getDirection() == Direction.FORWARD ? tunnels[1] : tunnels[0];
            default -> {
                String message = "Illegal train state: " + train.getTrainState() + "." +
                        "Allowable train states are FIRST_TUNNEL, SECOND_TUNNEL";
                throw new IllegalStateException(message); // TODO or custom exception?
            }
        };
    }

    private TunnelHolder() {
        if (instance != null) {
            throw new IllegalStateException("Already instantiated");
        }
        CustomFileReader fileReader = new CustomFileReaderImpl();
        CustomParser parser = new CustomParserImpl();
        try {
            List<String> lines = fileReader.readLines("src/main/resources/data/tunnels.txt");
            tunnels = parser.parseTunnels(lines).toArray(Tunnel[]::new);
            if (tunnels.length != MAX_TUNNEL_AMOUNT) {
                throw new TunnelException("The number of tunnels must be two. Number of tunnels in file: " + tunnels.length);
            }
        } catch (ReaderException | TunnelException e) { // TODO: 28.11.2021 так можно?
            throw new ExceptionInInitializerError(e);
        }
    }
}
