package edu.ntnu.idi.idatt.engine;

import edu.ntnu.idi.idatt.observer.GameEvent;
import edu.ntnu.idi.idatt.observer.Observable;
import edu.ntnu.idi.idatt.observer.events.Event;
import java.util.ArrayList;
import java.util.List;

/**
 *  The Dice class is responsible for creating and storing die objects, rolling the diceList,
 *  summing their values, and retrieving the value of a specific die.
 *
 * @version 1.1
 */
public class Dice extends Observable {
  private final List<Die> diceList;
  private int lastRolledValue;

  /**
   * The constructor constructs a new die objects based on numberOfDice and adds them to the list.
   *
   * @param numberOfDice the number of die objects to be constructed and added to the diceList list
   */

  public Dice(int numberOfDice) {
    this.diceList = new ArrayList<>();
    this.lastRolledValue = 0;

    for (int i = 0; i < numberOfDice; i++) {
      Die die = new Die();
      diceList.add(die);
    }
  }

  /**
   * Accessor method for diceList.
   *
   * @return diceList
   */
  public List<Die> getDiceList() {
    return diceList;
  }

  /**
   * Accessor method for lastRolledValue.
   *
   * @return lastRolledValue
   */
    public int getLastRolledValue() {
        return lastRolledValue;
    }

  /**
   * Rolls the diceList in the list individually and sums the values.
   *
   * @return the sum of the rolled diceList
   */
  public int roll() {
    int rollDice = 0;
    int roll;
    for (Die die : diceList) {
      roll = die.roll();
      rollDice += roll;
    }
    notifyObservers(new GameEvent(Event.ROLL_DICE, "Dice rolled: " + rollDice, null));
    lastRolledValue = rollDice;
    return rollDice;
  }

  /**
   * Retrieves the value of a specific die from the diceList list.
   *
   * @param dieNumber the index of the die subtracted by 1 to retrieve the value from.
   * @return the value of the die at the specified index
   * @throws IllegalArgumentException if the provided dieNumber is more or less than existing diceList
   */
  public int getDie(int dieNumber) {
    if (dieNumber <= 0 || dieNumber > diceList.size()) {
      throw new IllegalArgumentException("dieNumber cannot be less or more than existing diceList");
    }
    return diceList.get(dieNumber - 1).getValue();
  }
}


