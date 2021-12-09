package by.bsu.task6.entity.tunnel;

import by.bsu.task6.entity.train.Direction;
import by.bsu.task6.entity.train.Train;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private final Logger logger = LogManager.getLogger();
    private final int oneDirectionSeriesLimit;
    private final int attemptSeriesLimitToNeglectPriority;
    private final PassingTime passingTime;
    private final Semaphore trainsBeforeTunnelSemaphore;
    private final Semaphore forwardDirectionSemaphore;
    private final Semaphore reversedDirectionSemaphore;
    private final ReentrantLock lock;
    private final AtomicInteger currentForwardDirectionSeries;
    private final AtomicInteger currentReversedDirectionSeries;
    private final AtomicInteger attemptsToNeglectPriority;
    private final String name;
    private Direction priorityDirection;

    public Tunnel(int trainsInOneDirectionLimit, PassingTime passingTime, int oneDirectionSeriesLimit, String name) {
        this.passingTime = passingTime;
        this.oneDirectionSeriesLimit = oneDirectionSeriesLimit;
        this.name = name;
        attemptSeriesLimitToNeglectPriority = oneDirectionSeriesLimit * 4;
        trainsBeforeTunnelSemaphore = new Semaphore(trainsInOneDirectionLimit * 2, true);
        forwardDirectionSemaphore = new Semaphore(trainsInOneDirectionLimit, true);
        reversedDirectionSemaphore = new Semaphore(trainsInOneDirectionLimit, true);
        lock = new ReentrantLock();
        currentReversedDirectionSeries = new AtomicInteger();
        currentForwardDirectionSeries = new AtomicInteger();
        attemptsToNeglectPriority = new AtomicInteger();
    }

    public boolean tryAcceptTrain(Train train) {
        boolean isAccepted = false;
        try {
            lock.lock();
            logger.info(String.format("Train %d is trying to pass through tunnel %s", train.getId(), name));
            if (trainsBeforeTunnelSemaphore.availablePermits() == oneDirectionSeriesLimit * 2) { // тоннель пуст
                priorityDirection = train.getDirection();
            }
            if (train.getDirection() == priorityDirection) {
                if (getDirectionSeries(priorityDirection).incrementAndGet() <= oneDirectionSeriesLimit) {
                    isAccepted = true;
                } else { // достигнуто максимальное число поездов, прошедших подряд в одном направлении
                    resetDirectionSeries();
                    priorityDirection = priorityDirection.getOppositeDirection();
                    attemptsToNeglectPriority.set(0);
                }
            } else if (attemptsToNeglectPriority.incrementAndGet() > attemptSeriesLimitToNeglectPriority) {
                resetDirectionSeries();
                priorityDirection = train.getDirection();
                logger.info("Attempts count to neglect priority reached the limit. New priority direction: " + priorityDirection);
                getDirectionSeries(priorityDirection).incrementAndGet();
                isAccepted = true;
            }
            return isAccepted;
        } finally {
            lock.unlock();
            if (isAccepted) {
                givePermission(train);
            } else {
                logger.info(String.format("Tunnel %s. Permission denied for train %d in %s direction", name, train.getId(), train.getDirection()));
            }
        }
    }

    private void acceptTrain(Train train) {
        try {
            trainsBeforeTunnelSemaphore.acquire();
            acquireDirectionSemaphore(train);
            logger.info(String.format("Train %d entered tunnel %s in %s direction", train.getId(), name, train.getDirection()));
            TimeUnit.MILLISECONDS.sleep(passingTime.getMillis());
            logger.info(String.format("Train %d left tunnel %s", train.getId(), name));
        } catch (InterruptedException e) {
            logger.warn(e);
            Thread.currentThread().interrupt();
        } finally {
            releaseSemaphore(train);
            trainsBeforeTunnelSemaphore.release();
        }
    }

    private AtomicInteger getDirectionSeries(Direction direction) {
        if (direction == Direction.FORWARD) {
            return currentForwardDirectionSeries;
        } else {
            return currentReversedDirectionSeries;
        }
    }

    private void resetDirectionSeries() {
        currentForwardDirectionSeries.set(0);
        currentReversedDirectionSeries.set(0);
    }

    private void releaseSemaphore(Train train) {
        if (train.getDirection() == Direction.FORWARD) {
            forwardDirectionSemaphore.release();
        } else {
            reversedDirectionSemaphore.release();
        }
    }

    private void acquireDirectionSemaphore(Train train) throws InterruptedException {
        if (train.getDirection() == Direction.FORWARD) {
            forwardDirectionSemaphore.acquire();
        } else {
            reversedDirectionSemaphore.acquire();
        }
    }

    private void givePermission(Train train) {
        attemptsToNeglectPriority.set(0);
        acceptTrain(train);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tunnel{");
        sb.append("oneDirectionSeriesLimit=").append(oneDirectionSeriesLimit);
        sb.append(", attemptSeriesLimitToNeglectPriority=").append(attemptSeriesLimitToNeglectPriority);
        sb.append(", passingTime=").append(passingTime.getMillis()).append("millis");
        sb.append(", currentForwardDirectionSeries=").append(currentForwardDirectionSeries);
        sb.append(", currentReversedDirectionSeries=").append(currentReversedDirectionSeries);
        sb.append(", attemptsToNeglectPriority=").append(attemptsToNeglectPriority);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}