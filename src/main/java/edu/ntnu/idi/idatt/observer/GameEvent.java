package edu.ntnu.idi.idatt.observer;

import edu.ntnu.idi.idatt.model.Player;
import edu.ntnu.idi.idatt.observer.events.Event;
/**
 * Represents an event in the game, such as a player moving or a winner being declared.
 * This class is used to encapsulate the details of the event and the player associated with it.
 *
 * @version 1.0
 */
public class GameEvent {
  private final Event eventType;
  private final String eventDetails;
  private final Player player;

  /**
   * Constructor for GameEvent.
   *
   * @param eventType the event type (e.g., "player_move", "winner_declared")
   * @param eventDetails the event details (e.g., "player1 moved to (x,y)", "player1 wins")
   * @param player the player associated with the event
   */
  public GameEvent(Event eventType, String eventDetails, Player player) {
    this.eventType = eventType;
    this.eventDetails = eventDetails;
    this.player = player;
  }

  /**
   * Gets the player associated with the event.
   *
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Gets the event type.
   *
   * @return the event type
   */
  public Event getEventType() {
    return eventType;
  }

  /**
   * Gets the event details.
   *
   * @return the event details
   */
  public String getEventDetails() {
    return eventDetails;
  }

  /**
   * Returns a string representation of the GameEvent object.
   *
   * @return a string representation of the GameEvent
   */
  @Override
  public String toString() {
    return "GameEvent{"
        + "eventType='"
        + eventType
        + '\''
        + ", eventData='"
        + eventDetails
        + '\''
        + '}';
  }
}
