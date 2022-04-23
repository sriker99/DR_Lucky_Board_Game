package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import game.view.View;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements world controller interface and displays player information
 * by calling world object.
 */
public class PlayerInfo implements WorldController {
  private int playerIndex;
  private Scanner scan;
  private Appendable out;

  /**
   * This is a constructor which initializes a player info object with given parameters.
   *
   * @param scan is a scanner object.
   * @param out  is an appendable object.
   */
  public PlayerInfo(Scanner scan, Appendable out) {
    if (scan == null || out == null) {
      throw new IllegalArgumentException("Scanner and Appendable shouldn't be null.");
    }
    this.scan = scan;
    this.out = out;
    this.playerIndex = 0;
  }

  @Override
  public void playGame(World w, View view) throws IllegalArgumentException {
    if (w == null || view == null) {
      throw new IllegalArgumentException("Model and view cannot be null");
    }
    try {
      while (true) {
        try {
          this.playerIndex = Integer.parseInt(scan.nextLine());
          out.append(w.displayPlayerInfo(this.playerIndex));
          break;
        } catch (IllegalArgumentException ie) {
          out.append(ie.getMessage() + "\n" + "Enter the player index again\n");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException(ioe.getMessage());
    }
  }
}
