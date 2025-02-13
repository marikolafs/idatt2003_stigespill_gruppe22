package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.Die;
import java.util.ArrayList;

public class Dice {

  private ArrayList<Die> dice = new ArrayList<>();
  static Die die = new Die();

  public ArrayList<Die> Dice(int numberOfDice){
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
    return dice;
  }

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