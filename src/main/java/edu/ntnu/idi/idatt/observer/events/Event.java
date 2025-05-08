package edu.ntnu.idi.idatt.observer.events;

/**
 * The Event enum represents various events that can occur in the game. These events are used to
 * notify observers about changes in the game state.
 *
 * @version 1.2
 */
public enum Event {
    /**
     * Event when a player rolls the dice.
     */
    ROLL_DICE,

    /**
     * Event when a player moves to a new tile.
     */
    PLAYER_MOVED,

    /**
     * Event when a player leaves its tile.
     */
    PLAYER_LEFT_TILE,

    /**
     * Event when the game ends.
     */
    GAME_END,

    /**
     * Event when a player wins the game.
     */
    PLAYER_WIN,

    /**
     * Event when a player is added to the game.
     */
    PLAYER_ADDED,

    /**
     * Event when board is created.
     */
    BOARD_CREATED,

    /**
     * Event when dice is created.
     */
    DICE_CREATED,

    /**
     * Event when game is started.
     */
    GAME_START,

    /**
     * Event when a tile action is performed.
     */
    TILE_ACTION,
    /**
     * Event when a player changes.
     */
    PLAYER_CHANGE

}
