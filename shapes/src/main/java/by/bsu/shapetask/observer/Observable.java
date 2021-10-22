package by.bsu.shapetask.observer;

public interface Observable {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();
}
