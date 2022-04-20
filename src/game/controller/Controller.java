package game.controller;

import game.view.View;

/**
 * This interface is for declaring method signatures that are implemented by Extensible controller.
 */
public interface Controller {

  /**
   * This method implements execute functionality, it is called to the world model.
   *
   * @param w is the world model or interface.
   */
  public void execute(View view);
}
