package game.model;

/**
 * This class describes Item with its name,damage,index.
 */
public class Item implements Thing {

  private final String name;
  private final int damage;
  private int indexOfRoom;

  /**
   * This is a constructor which initializes a move-player object with given parameters.
   *
   * @param name        of the item.
   * @param damage      is a damage value of the item.
   * @param indexOfRoom is an index of the room.
   */
  public Item(String name, int damage, int indexOfRoom) throws IllegalArgumentException {

    if (name == null || name.trim().equals("") || damage < 0 || indexOfRoom < 0) {
      throw new IllegalArgumentException(
          "Item name shouldn't be empty and damage and index shouldn't be less than 0");
    }
    this.name = name;
    this.damage = damage;
    this.indexOfRoom = indexOfRoom;
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getIndexOfRoom() {
    return this.indexOfRoom;
  }

}
