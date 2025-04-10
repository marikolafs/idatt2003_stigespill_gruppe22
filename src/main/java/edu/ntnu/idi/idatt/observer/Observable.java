package edu.ntnu.idi.idatt.observer;

import java.util.ArrayList;
import java.util.List;


public abstract class Observable {
    private final List<BoardGameObserver> observers;

    protected Observable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(BoardGameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BoardGameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(GameEvent event) {
        for (BoardGameObserver observer : observers) {
        observer.stateChanged(event);
        }
    }
}
