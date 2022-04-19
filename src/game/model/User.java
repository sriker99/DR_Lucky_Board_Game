package game.model;

/**
 * This enum used to differentiate between player types.
 */
public enum User {
  COMPUTER("COMPUTER"), HUMAN("HUMAN");

  private final String type;

  /**
   * This constructor initialises the player type.
   *
   * @param type of the player computer or human.
   */
  User(String type) {
    if (type == null || "".equals(type.trim())) {
      throw new IllegalArgumentException("Type of player is invalid.");
    }
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
