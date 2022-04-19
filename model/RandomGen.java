package game.model;

import java.util.Random;

/**
 * This class generates a random number by implementing the methods from RandomGenerator interface.
 */
public class RandomGen implements RandomGenerator {

  private Random randomNumber;

  /**
   * This constructor initializes the random generator object.
   */
  public RandomGen() {
    this.randomNumber = new Random();
  }

  @Override
  public int getRandomNumber() {
    return Math.abs(randomNumber.nextInt());
  }
}
