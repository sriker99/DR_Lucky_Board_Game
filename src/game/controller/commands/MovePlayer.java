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
public class MovePlayer implements WorldController {
 private int x;
 private int y;

  /**
   * This is a constructor which initializes a move-player object with given parameters.
   *
   * @param x position on X-axis.
   * @param y position on Y-axis.
   */
  public MovePlayer(int x, int y) {
    this.x=x;
    this.y=y;
  }

  @Override
  public void playGame(World w, View view) {
      if (w == null || view == null) {
        throw new IllegalArgumentException("Model and view cannot be null");
      }
      try {
        w.movePlayer(x, y);
    } catch (IllegalArgumentException ise) {
      view.showErrorMessage(ise.getMessage());
  }
  }
}
