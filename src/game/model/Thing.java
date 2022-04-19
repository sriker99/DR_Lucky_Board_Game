package game.model;

/**
 * This interface declares method signatures for item class.
 */
public interface Thing {

  /**
   * This method finds the damage by the item.
   *
   * @return returns the damage value.
   */
  public int getDamage();

  /**
   * This method finds the name of the item.
   *
   * @return returns the item's name.
   */
  public String getName();

  /**
   * This method finds the index of the room in which item is present.
   *
   * @return returns the index of the room.
   */
  public int getIndexOfRoom();
}
