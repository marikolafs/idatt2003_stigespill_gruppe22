package edu.ntnu.idi.idatt.observer;

import java.util.ArrayList;
import java.util.List;


/**
 * The Observable class is responsible for managing a list of observers and notifying them of
 * changes in the game state. It provides methods to add, remove, and notify observers.
 *
 * @version 1.0
 */
public abstract class Observable {
    private static List<BoardGameObserver> observers;

    protected Observable() {
        observers = new ArrayList<>();
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
    public static void notifyObservers(GameEvent event) {
        for (BoardGameObserver observer : observers) {
        observer.stateChanged(event);
        }
    }
}
