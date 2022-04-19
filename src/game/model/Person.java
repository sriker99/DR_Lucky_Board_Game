package game.model;

/**
 * This interface declares method signatures for Target Class and player Class.
 */
public interface Person {

  /**
   * This method finds the health of the person.
   *
   * @return returns the health value.
   */
  public int getHealth();

  /**
   * This method finds the index of the space where the target is present.
   *
   * @return returns the current position of the target with respective to space index.
   */
  public int getCurrentSpaceIndex();

  /**
   * This method updates the location of the target.
   *
   * @param index is the updated location of the target.
   */
  public void setCurrentSpaceIndex(int index);

  /**
   * This method decreases the health by given value.
   *
   * @param i is the value that needs to be decreased.
   */
  void decreaseHealthBy(int i);
}
