package edu.ntnu.idi.idatt;

public class BoardGame {

  static Dice dice = new Dice();
  static Die die = new Die();

  static int numberOfDice = 2;

  public void createDice(){

  }

  public static void rollOnce(){
    dice.roll();
  }

  public static void printRolls(){
    for (int i = 0; i < numberOfDice; i++){
      System.out.println("dice " + (i + 1) + " rolled " + dice.getDie(i));
    }
  }

  public static void main(String[] args) {
    dice.Dice(numberOfDice);
    System.out.println("one dice rolled: " + die.Roll());
    System.out.println("get value: " + die.GetValue());
    dice.roll();
  }
}
