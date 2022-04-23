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
  private String spaceName;
  private Scanner scan;
  private Appendable out;

  /**
   * This is a constructor which initializes a move-player object with given parameters.
   *
   * @param scan is a scanner object.
   * @param out  is an appendable object.
   */
  public MovePlayer(Scanner scan, Appendable out) {
    if (scan == null || out == null) {
      throw new IllegalArgumentException("Scanner and Appendable shouldn't be null.");
    }
    this.scan = scan;
    this.out = out;
    this.spaceName = "";
  }

  @Override
  public void playGame(World w, View view) {
    try {
      if (w == null || view == null) {
        throw new IllegalArgumentException("Model and view cannot be null");
      }
      while (true) {
        try {
          this.spaceName = scan.nextLine();
          w.movePlayer(this.spaceName);
          out.append(String.format("Player has been moved to %s\n", spaceName));
          break;
        } catch (IllegalArgumentException ie) {
          out.append(ie.getMessage() + "\n" + "Enter the space name again\n");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException(ioe.getMessage());
    }
  }
}
