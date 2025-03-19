package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.engine.Die;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
/**
 * The DieTest class contains unit tests for the Die class.
 * It ensures that the Die class methods work as expected.
 */
public class DieTest {
  private Die die;

  @BeforeEach
  void setUp() {
    die = new Die();
  }


  @Test
  @DisplayName("Should roll a random number from 1-6 inclusive")
  void testRoll() {
    int rollValue = die.roll();

    assertTrue(1<= rollValue && rollValue <= 6,
        "The roll value should be between 1-6 inclusive");
  }

  @Test
  @DisplayName("Should return last rolled value after rolling")
  void testGetValueAfterRoll() {
    int rollValue = die.roll();
    int lastValue = die.getValue();

    assertEquals(rollValue, lastValue, "getValue() should return the last rolled value");
  }
}
