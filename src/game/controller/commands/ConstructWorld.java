package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import game.view.View;

/**
 * This command class implements WorldController interface and creates a graphical image by calling
 * the world-class methods.
 */
public class ConstructWorld implements WorldController {
  @Override
  public void playGame(World w, View view) throws IllegalArgumentException {
    if (w == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    w.constructWorld();
  }
}
