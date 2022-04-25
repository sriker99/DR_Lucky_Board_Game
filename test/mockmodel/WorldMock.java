package mockmodel;

import game.model.World;
import java.awt.image.BufferedImage;

/**
 * This mock class implements world interface to mock the actions performed by
 * world class.
 */
public class WorldMock implements World {
  private final int uniqueCode;
  private StringBuilder log;
  private boolean gameOver;

  /**
   * This is a constructor to initialize the world mock object with given values.
   *
   * @param log        is an input appender.
   * @param uniqueCode is a unique value to differentiate between methods.
   */
  public WorldMock(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
    gameOver = false;
  }

  @Override
  public void constructWorld() {
    log.append(String.format("constructing world %d\n", uniqueCode));
  }

  @Override
  public void addPlayer(String name, String spaceName) {
    log.append(
        String.format("adding player %s in space %s with %d\n", name, spaceName, uniqueCode));
  }

  @Override
  public void pickItem(String item) {
    log.append("Inputs:\n Item name:" + item);
    log.append(String.format("\n  picked item %d\n", uniqueCode));
    gameOver = true;
  }

  @Override
  public String lookAround() {
    StringBuffer res = new StringBuffer();
    res.append(String.format("Look around is invoked %d\n", uniqueCode));
    gameOver = true;
    return res.toString();
  }

  @Override
  public String displayPlayerInfo(int i) {
    StringBuffer res = new StringBuffer();
    log.append("Input:\n  Enter the player index: " + i + "\n");
    res.append(String.format("display player info %d\n", uniqueCode));
    return res.toString();
  }


  @Override
  public void addComputerPlayer() {
    log.append(
        String.format("adding computer player ravi in space Dining Hall with %d\n", uniqueCode));
  }

  @Override
  public String checkComputerPlayer() {
    String s = String.format("checking computer player %d\n", uniqueCode);
    return s;
  }

  @Override
  public void movePet(String spaceName) {
    log.append("Input:\n  Enter the space name: " + spaceName + "\n");
    log.append(String.format("Moving pet %d\n", uniqueCode));
    gameOver = true;
  }

  @Override
  public void attack(String itemName) {
    log.append("Input:\n  Enter the item name: " + itemName + "\n");
    log.append(String.format("Target has been attacked %d\n", uniqueCode));
    gameOver = true;
  }

  @Override
  public String displayClues() {
    String s = String.format("Displaying clues %d\n", uniqueCode);
    return s;
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public String getWinner() {
    String s = String.format("Player %d\n", uniqueCode);
    return s;
  }

  @Override
  public String[] getSpaces() {
    log.append("Get spaces method is invoked\n");
    String[] result;
    result = new String[1];
    result[1] = String.format("unique code %d\n", uniqueCode);
    return result;
  }

  @Override
  public String[] getPlayerItems() {
    log.append("Get player items method is invoked\n");
    String[] result;
    result = new String[1];
    result[1] = String.format("unique code %d\n", uniqueCode);
    return result;
  }

  @Override
  public String[] getSpaceItems() {
    log.append("Get space items method is invoked\n");
    String[] result;
    result = new String[1];
    result[1] = String.format("unique code %d\n", uniqueCode);
    return result;
  }

  @Override
  public BufferedImage cropImage() {
    log.append("Get spaces method is invoked\n");
    log.append(String.format("unique code %d\n", uniqueCode));
    return null;
  }

  @Override
  public String movePlayer(int x, int y) {
    log.append("move Player method is invoked\n");
    log.append(String.format("Inputs: x and y : %d %d \n", x, y));
    return String.format("unique code %d\n", uniqueCode);
  }

  @Override
  public String getPlayerTurn() {
    log.append("get player turn method is invoked\n");
    return String.format("unique code %d\n", uniqueCode);
  }

  @Override
  public String displaySpaceInfo(String s) {
    return null;
  }

  @Override
  public void movePlayerComp(String nextSpaceName) {

  }
}
