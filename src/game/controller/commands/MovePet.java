package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import game.view.View;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements world controller interface and moves the player to next space given
 * by calling world object.
 */
public class MovePet implements WorldController {
  private String location;

  /**
   * This constructor initializes move pet object with the given inputs.
   * @param location where the pet to be moved.
   */
  public MovePet(String location) {
    if (location == null || "".equals(location.trim())) {
      throw new IllegalArgumentException("Location shouldn't be empty.");
    }
    this.location = location;
  }

  @Override
  public void playGame(World w, View view) throws IllegalArgumentException {
    if (w == null|| view == null) {
      throw new IllegalArgumentException("Model and view cannot be null");
    }
    w.movePet(location);
    view.showSuccessMessage("Pet Status", "Pet has been moved.");
  }
}
