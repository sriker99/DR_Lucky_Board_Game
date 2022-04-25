package game.model;

/**
 * This interfaces declares method signatures for constructing a World.
 */
public interface World extends ReadOnlyWorld {

  /**
   * This method creates a world from the given parameters.
   */
  public void constructWorld();

  /**
   * This method moves the player to the position where user clicked.
   *
   * @param x is the x coordinate.
   * @param y is the y coordinate.
   */
  String movePlayer(int x, int y);


  /**
   * Add player to the world and players list.
   *
   * @param name      name of the player to be added.
   * @param spaceName position of the player to be added.
   */
  public void addPlayer(String name, String spaceName);

  /**
   * Picking an item from the current space.
   *
   * @param item is the item that needs to be picked.
   */
  public void pickItem(String item);

  /**
   * This method displays the description about a player.
   *
   * @param i is the index of the player.
   * @return description of the player.
   */
  public String displayPlayerInfo(int i);


  /**
   * Adds a computer player when player chooses.
   */
  public void addComputerPlayer();

  /**
   * Perform random actions if current player is of computer type.
   *
   * @return status of the computer player.
   */
  public String checkComputerPlayer();

  /**
   * Moves pet from one location to another.
   *
   * @param spaceName name of the space where pet to be moved.
   */
  public void movePet(String spaceName);

  /**
   * Player attacks the target with the given item.
   *
   * @param itemName name of the item with which target to be attacked.
   */
  public void attack(String itemName);

  /**
   * Checks whether the game is over.
   *
   * @return the boolean value.
   */
  public boolean isGameOver();

  /**
   * Finds the winner of the game.
   *
   * @return the winner if there is one else null.
   */
  public String getWinner();

  /**
   * Finds the player turn.
   *
   * @return type of player who has to play.
   */
  public String getPlayerTurn();

}
