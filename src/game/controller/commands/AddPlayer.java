package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements WorldController interface and adds a player to
 * the world using world methods.
 */
public class AddPlayer implements WorldController {
  String name;
  String location;

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
  public void playGame(World w) throws IllegalArgumentException {
    if (w == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    w.addPlayer(name, location);

  }
}
