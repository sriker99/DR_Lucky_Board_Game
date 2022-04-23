package game.controller;

import game.model.World;
import game.view.View;

/**
 * This interface is for declaring method signatures used by command class.
 */
public interface WorldController {

  /**
   * Starting point for the controller.
   *
   * @param w the model to use
   * @throws IllegalArgumentException if an invalid model is provided
   */
  void playGame(World w, View view) throws IllegalArgumentException;
}
