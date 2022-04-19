package game.model;


/**
 * This class describes target with its name,health and its current position.
 */
public class Target implements Person {

  private final String name;
  private int currentSpaceIndex;
  private int health;

  /**
   * This constructor checks for invalid inputs and initializes the parameters.
   *
   * @param name              Name of the target.
   * @param health            health of the target.
   * @param currentSpaceIndex current position of the target.
   * @throws IllegalArgumentException if the arguments doesn't meet the standards.
   */
  public Target(String name, int health, int currentSpaceIndex) throws IllegalArgumentException {
    if (name == null || health < 0 || name.trim().equals("") || currentSpaceIndex < 0) {
      throw new IllegalArgumentException(
          "Health shouldn't be less than 0 and name shouldn't be empty");
    }
    this.name = name;
    this.health = health;
    this.currentSpaceIndex = currentSpaceIndex;
  }

  @Override
  public int getHealth() {
    return this.health;
  }


  @Override
  public int getCurrentSpaceIndex() {
    return currentSpaceIndex;
  }

  @Override
  public void setCurrentSpaceIndex(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("index is invalid");
    }
    currentSpaceIndex = index;
  }

  @Override
  public void decreaseHealthBy(int i) {
    if (i <= 0) {
      throw new IllegalArgumentException("Invalid health.");
    }
    this.health = this.health - i;
  }

  @Override
  public String toString() {
    String target = String.format("Target name is %s, health is %d, current location is %d\n",
        name, health, currentSpaceIndex);
    return target;
  }
}
