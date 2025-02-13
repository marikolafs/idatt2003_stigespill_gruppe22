package edu.ntnu.idi.idatt;

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

  public int getDie(int dieNumber){
    for (Die die : dice) {
      if(dieNumber == die.GetNumber()){
        int dieRoll = die.GetValue();
      }
    }
    return roll();
  }
}

/*I dette spillet skal det benyttes terninger (engelsk: Dice). Samlingen av alle terninger er representert
av klassen Dice. En enkelt terning er representert av klassen Die. Terningene rulles samtidig, og
øynene summeres.*/