package edu.ntnu.idi.idatt.engine;

import java.util.Random;

/**
 * The die class represents a single die that can be rolled to generate a random value between
 * 1-6 (inclusive).
 * This class provides functionality to roll the die, store its last value
 * and retrieve it when needed.
 */
public class Die {
  private int lastRolledValue;
  private final Random rand = new Random();


  /**
   * Method to get the last rolled value of the die.
   *
   * @return the last rolled value of the die
   */
  public int getValue() {
    return lastRolledValue;
  }


  /**
   * Rolls the die and generates a random number between 1-6 (inclusive).
   * The rolled value is stored as the last rolled value.
   *
   * @return the last rolled value
   */
  public int roll() {
    int max = 6;
    int min = 1;

    int rolledValue = rand.nextInt(max - min + 1) + min;
    lastRolledValue = rolledValue;

    return rolledValue;
  }

}