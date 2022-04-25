package game.controller;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 *
 * <p>Each function is designed to take in the necessary data to complete that
 * functionality.
 */
public interface Features {

  /**
   * Exits the game.
   */
  void exitProgram();

  /**
   * This method adds player to the game.
   *
   * @param playerType     type of player whether human or computer.
   * @param playerName     name of the player.
   * @param playerLocation location of the player where to start the game.
   */
  void addPlayer(String playerType, String playerName, String playerLocation);

  /**
   * Attack the target.
   *
   * @param item with which target should be attacked.
   */
  void attack(String item);

  /**
   * Displays the item dialog to choose an item.
   *
   * @param title of the dialog
   * @param items name of the items that should be present in the options.
   */
  void displayItemsDialog(String title, String[] items);

  /**
   * Displays dialog box when there is an error.
   *
   * @param msg to be displayed in the dialog box.
   */
  void displayErrorDialog(String msg);

  /**
   * Displays look around description in the dialog box.
   */
  void displayLookAround();

  /**
   * Pick item from the space.
   *
   * @param item that should be picked from the spoce.
   */
  void pick(String item);

  /**
   * Start the game.
   */
  void startGame();

  /**
   * Switching to welcome screen when a new game is started.
   */
  void switchToWelcomeScreen();

  /**
   * Switching to adding players screen.
   */
  void switchToPlayerConfigScreen();

  /**
   * Switching to the game screen after the configuration is setup.
   */
  void switchToGameScreen();

  /**
   * Start a new game with the specifications given by the player.
   *
   * @param filePath location of the txt file.
   */
  void playGameWithUploadedFile(String filePath);

  /**
   * moving the pet in the world.
   *
   * @param location where pet should be moved.
   */
  void movePet(String location);

  /**
   * moving player in the world.
   *
   * @param x coordinate on X-axis
   * @param y coordinate on Y-axis
   */
  void movePlayer(int x, int y);
}
