package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.engine.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
  private Dice dice;

  @BeforeEach
  void setUp() {
    dice = new Dice(2);
  }
  @Nested
  @DisplayName("Positive tests for Dice")
  class PositiveTest {
    @Test
    @DisplayName("Should roll dice and return sum of rolled values")
    void testRoll() {
      int sum = dice.roll();
      assertTrue(sum >= 2 && sum <= 12,
          "The sum of two dice rolls should be between 2 and 12 inclusive");
    }

    @Test
    @DisplayName("Should get value of specific die")
    void testGetDie() {
      dice.roll();
      int dieValue = dice.getDie(1);
      assertTrue(dieValue >= 1 && dieValue <= 6,
          "The value of a die should be between 1 and 6");
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests{
    @Test
    @DisplayName("Should throw IllegalArgumentException for invalid die number")
    void testGetDieInvalidNumber () {
      assertThrows(IllegalArgumentException.class, () -> dice.getDie(0),
          "Should throw exception for die number 0");

      assertThrows(IllegalArgumentException.class, () -> dice.getDie(3),
          "Should throw exception for die number greater than number of dice");
    }
  }
}
