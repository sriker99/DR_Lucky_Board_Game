package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import game.view.View;

/**
 * This command class implements WorldController interface and adds a player to
 * the world using world methods.
 */
public class AddPlayer implements WorldController {
  private String name;
  private String location;

  /**
   * This constructor initialises the add player command with the given description of the player.
   *
   * @param name     of the player.
   * @param location of the player.
   */
  public AddPlayer(String name, String location) {
    if (name == null || "".equals(name.trim())) {
      throw new IllegalArgumentException("Player name cannot be empty");
    }
    if (location == null || "".equals(location.trim())) {
      throw new IllegalArgumentException("Player location cannot be empty");
    }
    this.name = name;
    this.location = location;
  }

  @Override
  public void playGame(World w, View view) throws IllegalArgumentException {
    if (w == null || view == null) {
      throw new IllegalArgumentException("Model and view cannot be null");
    }
    w.addPlayer(name, location);

  }
}
