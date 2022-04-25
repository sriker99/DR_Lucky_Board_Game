package game.model;

import java.util.List;

/**
 * This is an interface which extends Person interface and contains method.
 * signatures for Player class.
 */
public interface Player {

  /**
   * This method is to find the items names of the player.
   *
   * @return names of the items of the player
   */
  public List<String> getItems();

  /**
   * This method is used to find the index of the player.
   *
   * @return the current index of the player.
   */
  public int getPlayerIndex();

  /**
   * This method is used to set the player index.
   *
   * @param index of the current player.
   */
  public void setPlayerIndex(int index);

  /**
   * This method is used to find the limit of items for the player.
   *
   * @return the limit value.
   */
  public int getItemLimit();

  /**
   * This method add the given item to the players list.
   *
   * @param item name of the item to be added.
   */
  public void addItem(String item);

  /**
   * This method finds the name of the player.
   *
   * @return the current name of the player.
   */
  public String getPlayerName();

  /**
   * This method finds the type of the player.
   *
   * @return the type of the player.
   */
  public String getPlayerType();

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
   * This method removes item from the player.
   *
   * @param item name of the item.
   */
  void removeItem(String item);

  /**
   * This method accesses the x coordinate.
   * @return x value
   */
  int getX();

  /**
   * This method accesses the y coordinate.
   * @return y value
   */
  int getY();

  /**
   * This method sets x value.
   * @param x value
   */
  void setX(int x);

  /**
   * This method sets y value.
   * @param y value.
   */
  void setY(int y);
}
