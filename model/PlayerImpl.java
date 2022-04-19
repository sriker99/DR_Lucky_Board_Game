package game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements Player interface and defines its methods.
 */
public class PlayerImpl implements Player {

  private final String name;
  private final User userType;
  private int currentSpaceIndex;
  private List<String> itemList;
  private int playerIndex;
  private int itemLimit;

  /**
   * This is a constructor which initializes a player object with given parameters.
   *
   * @param name              of the player.
   * @param currentSpaceIndex is the initial position of the player in the world.
   * @param playerIndex       is the turn of the player.
   * @param userType          is whether user is computer or human.
   */
  public PlayerImpl(String name, int currentSpaceIndex, int playerIndex, User userType) {
    if (name == null || name.trim().equals("") || currentSpaceIndex < 0 || playerIndex < 0
        || (userType != User.HUMAN && userType != User.COMPUTER)) {
      throw new IllegalArgumentException(
          "Player name or currentSpaceIndex or UserType is invalid.");
    }

    this.itemList = new ArrayList<String>();
    this.name = name;
    this.currentSpaceIndex = currentSpaceIndex;
    this.playerIndex = playerIndex + 1;
    this.itemLimit = 3;
    this.userType = userType;
  }


  @Override
  public int getCurrentSpaceIndex() {
    return this.currentSpaceIndex;
  }

  @Override
  public void setCurrentSpaceIndex(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("Index is invalid");
    }
    this.currentSpaceIndex = index;
  }

  @Override
  public void removeItem(String item) {
    if (item == null || "".equals(item.trim()) || !itemList.contains(item.trim())) {
      throw new IllegalArgumentException("Item is invalid");
    }
    this.itemLimit++;
    itemList.remove(itemList.indexOf(item));
  }

  @Override
  public List<String> getItems() {
    List<String> itemListCopy = new ArrayList<>(this.itemList);
    return itemListCopy;
  }

  @Override
  public int getPlayerIndex() {
    return this.playerIndex;
  }

  @Override
  public void setPlayerIndex(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("Index is invalid");
    }
    this.playerIndex = index;
  }

  @Override
  public int getItemLimit() {
    return this.itemLimit;
  }

  @Override
  public void addItem(String item) {
    if (item == null || "".equals(item.trim())) {
      throw new IllegalArgumentException("item is invalid");
    }
    this.itemLimit--;
    itemList.add(item);
  }

  @Override
  public String getPlayerName() {
    return this.name;
  }

  @Override
  public String getPlayerType() {
    return this.userType.toString();
  }

  @Override
  public String toString() {
    String player = String.format("Player name is %s, and its location is %d,  carrying items %s\n",
        name, currentSpaceIndex, itemList.toString());
    return player;
  }
}
