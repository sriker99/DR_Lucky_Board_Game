package mockmodel;

import game.model.World;

/**
 * This mock class implements world interface to mock the actions performed by world class.
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
  public String displaySpaceInfo(String space) {
    StringBuffer res = new StringBuffer();
    log.append(String.format("Input:\n"
        + "  Enter the player space name: %s\n", space));
    res.append(String.format("display space info %d\n", uniqueCode));
    return res.toString();
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
  public void movePlayer(String spaceName) {
    log.append(String.format("Inputs:\n"
        + " Space Name:Kitchen\nmoved to space %d\n", uniqueCode));
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
  public String getTurn() {
    String s = String.format("get turn %d\n", uniqueCode);
    return s;
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
}
