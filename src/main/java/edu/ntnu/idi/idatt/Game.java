package edu.ntnu.idi.idatt;

public class Game {
  private int numberOfTiles;
  private int numberOfDice;

  public Game(int numberOfTiles, int numberOfDice, int numberOfPlayers) {
    this.numberOfTiles = numberOfTiles;
    this.numberOfDice = numberOfDice;
  }

  public int getNumberOfTiles() {
    return numberOfTiles;
  }

  public int getNumberOfDice() {
    return numberOfDice;
  }
}
