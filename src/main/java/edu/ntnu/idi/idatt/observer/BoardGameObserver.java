package edu.ntnu.idi.idatt.observer;


import edu.ntnu.idi.idatt.observer.events.Event;

/**
 * Interface for observing game events.
 */
public interface BoardGameObserver {

  /**
   * Called when a game event occurs.
   * This method is used to update the observer with the event details.
   *
   * @param event the event
   */
  void stateChanged(GameEvent event);


}
