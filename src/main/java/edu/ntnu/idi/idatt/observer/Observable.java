package edu.ntnu.idi.idatt.observer;

import java.util.ArrayList;
import java.util.List;


public abstract class Observable {
    private final List<BoardGameObserver> observers;

    protected Observable() {
        this.observers = new ArrayList<>();
    }

    /**
     * Gets the list of observers.
     *
     * @return the list of observers
     */
    public List<BoardGameObserver> getObservers() {
        return observers;
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to be added
     */
    public void addObserver(BoardGameObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer the observer to be removed
     */
    public void removeObserver(BoardGameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of a state change.
     *
     * @param event the event to be passed to the observers
     */
    public void notifyObservers(GameEvent event) {
        for (BoardGameObserver observer : observers) {
        observer.stateChanged(event);
        }
    }
}
