package game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It implements space interface with all the necessary parameters like its
 * dimensions and name.
 */
public class SpaceImpl implements Space {

  private final int upperLeftRow;
  private final int upperLeftCol;
  private final int lowerRightRow;
  private final int lowerRightCol;
  private final String nameOfSpace;
  private List<String> items;
  private List<String> playersInSpace;

  /**
   * This constructor checks for illegal coordinates and throws exception for it
   * and initializes. the given parameters.
   *
   * @param upperLeftRow  top left row value
   * @param upperLeftCol  top left column value
   * @param lowerRightRow bottom right row value
   * @param lowerRightCol bottom right column value
   * @param nameOfSpace   space name
   * @throws IllegalArgumentException if the arguments doesn't meet the standards.
   */
  public SpaceImpl(int upperLeftRow, int upperLeftCol, int lowerRightRow, int lowerRightCol,
                   String nameOfSpace) throws IllegalArgumentException {
    if ((upperLeftRow >= lowerRightRow) || (upperLeftCol >= lowerRightCol) || upperLeftRow < 0
        || lowerRightRow < 0 || upperLeftCol < 0 || lowerRightCol < 0) {
      throw new IllegalArgumentException("Invalid Coordinates");
    }
    if (nameOfSpace == null || nameOfSpace.trim().equals("")) {
      throw new IllegalArgumentException("Space name shouldn't be empty");
    }
    this.items = new ArrayList<String>();
    this.upperLeftRow = upperLeftRow;
    this.upperLeftCol = upperLeftCol;
    this.lowerRightRow = lowerRightRow;
    this.lowerRightCol = lowerRightCol;
    this.nameOfSpace = nameOfSpace;
    this.playersInSpace = new ArrayList<String>();
  }

  @Override
  public int getLowerRightCol() {
    return this.lowerRightCol;
  }

  @Override
  public int getLowerRightRow() {
    return this.lowerRightRow;
  }

  @Override
  public int getUpperLeftCol() {
    return this.upperLeftCol;
  }

  @Override
  public int getUpperLeftRow() {
    return this.upperLeftRow;
  }

  @Override
  public String getNameOfSpace() {
    return this.nameOfSpace;
  }

  @Override
  public List<String> getItems() {
    List<String> itemsCopy = new ArrayList<>(this.items);
    return itemsCopy;
  }

  @Override
  public void setItems(String itemsList) {
    if (itemsList == null || "".equals(itemsList.trim())) {
      throw new IllegalArgumentException("Items shouldn't be empty");
    }
    this.items.add(itemsList);
  }

  @Override
  public void removeItem(String item) {
    if (item == null || "".equals(item.trim()) || !this.items.contains(item.trim())) {
      throw new IllegalArgumentException("Item shouldn't be empty");
    }
    this.items.remove(item);
  }

  @Override
  public List<String> getPlayersInSpace() {
    List<String> playersCopy = new ArrayList<>(this.playersInSpace);
    return playersCopy;
  }

  @Override
  public void addPlayersInSpace(String player) {
    if (player == null || "".equals(player.trim()) || this.playersInSpace.contains(player.trim())) {
      throw new IllegalArgumentException("Player name already exsits");
    }
    this.playersInSpace.add(player);
  }

  @Override
  public void removePlayerFromSpace(String player) {
    if (player == null || "".equals(player.trim()) || !playersInSpace.contains(player.trim())) {
      throw new IllegalArgumentException("Player name is invalid.");
    }
    this.playersInSpace.remove(player);
  }

}
