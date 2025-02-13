package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.Die;
import java.util.ArrayList;

/**
 *  The Dice class is responsible for creating and storing die objects, rolling the dice,
 *  summing their values, and retrieving the value of a specific die.
 *
 * @version 1.0
 */
public class Dice {

  private ArrayList<Die> dice = new ArrayList<>();
  static Die die = new Die();

  /**
   * Creates new die objects and adds them to the dice list.
   *
   * @param numberOfDice objects to be created and added
   */
  public ArrayList<Die> Dice(int numberOfDice){
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
    return dice;
  }

  /**
   * Rolls the dice in the list individually and sums the values.
   *
   * @return the sum of the rolled dice
   */
  public int roll(){
    int rollDice = 0;
    int roll;
    for (Die die : dice) {
      roll = die.Roll();
      System.out.println("rolled " + roll);
      rollDice += roll;
    }
    System.out.println("sum of dice " + rollDice);
    return rollDice;
  }

  /**
   * Retrieves the value of a specific die from the dice list.
   *
   * @param dieNumber the index of the die subtracted by 1 to retrieve the value from.
   * @return the value of the die at the spedified index
   * @throws IllegalArgumentException if the provided dieNumber is more or less than existing dice
   */
  public int getDie(int dieNumber){
    if (dieNumber <= 0 || dieNumber > dice.size()) {
    throw new IllegalArgumentException("dieNumber cannot be less or more than existing dice");
  }
    return dice.get(dieNumber - 1).getValue();
  }
}

/*I dette spillet skal det benyttes terninger (engelsk: Dice). Samlingen av alle terninger er representert
av klassen Dice. En enkelt terning er representert av klassen Die. Terningene rulles samtidig, og
øynene summeres.*/