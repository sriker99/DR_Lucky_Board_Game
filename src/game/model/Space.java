package game.model;

import java.util.List;

/**
 * this interface space has methods for returning the details of the space object such.
 * as coordinates and name of the space.
 */
public interface Space {

  /**
   * It returns the lower right column value.
   *
   * @return lower right column value.
   */
  public int getLowerRightCol();

  /**
   * It returns the lower right row value.
   *
   * @return lower right row value.
   */
  public int getLowerRightRow();

  /**
   * It returns the upper left column value.
   *
   * @return upper left column value.
   */
  public int getUpperLeftCol();

  /**
   * It returns the upper left row value.
   *
   * @return the upper left row value.
   */
  public int getUpperLeftRow();

  /**
   * It returns the name of the space.
   *
   * @return space name
   */
  public String getNameOfSpace();

  /**
   * This method finds the items present in the space.
   *
   * @return the item name in the list format.
   */
  public List<String> getItems();

  /**
   * This method sets the items list for the given space.
   *
   * @param itemsList list of the items of that particular space.
   */
  public void setItems(String itemsList);

  /**
   * This method removes item from the given space.
   *
   * @param item to be removed from the space.
   */
  public void removeItem(String item);

  /**
   * This method finds players list present in the room.
   *
   * @return the list of player objects.
   */
  public List<String> getPlayersInSpace();

  /**
   * This method adds players to the space.
   *
   * @param player the player that to be added to the space.
   */
  public void addPlayersInSpace(String player);

  /**
   * This method removes the player from the space.
   *
   * @param player the player that to be removed from the space.
   */
  public void removePlayerFromSpace(String player);

}
