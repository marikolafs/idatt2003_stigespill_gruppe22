package edu.ntnu.idi.idatt;

import java.util.Random;

public class Die {

  private int lastRolledValue;
  private int number;
  Random rand = new Random();
  int max = 6, min = 1;

  public Die() {
    this.lastRolledValue = lastRolledValue;
    this.number = number;
  }

  public int GetValue(){
    return lastRolledValue;
  }

  public int GetNumber(){
    return number;
  }

  public int Roll(){
    int rolledValue = rand.nextInt(max - min + 1) + min;
    lastRolledValue = rolledValue;
    return rolledValue;
  }

  public int setNumber(int number){
    this.number = number;
    return number;
  }
}
/*I dette spillet skal det benyttes terninger (engelsk: Dice). Samlingen av alle terninger er representert
av klassen Dice. En enkelt terning er representert av klassen Die. Terningene rulles samtidig, og
øynene summeres.*/