package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements world controller interface and displays space information
 * by calling world object.
 */
public class SpaceInfo implements WorldController {
  private String spaceName;
  private Scanner scan;
  private Appendable out;

  /**
   * This is a constructor which initializes a space info object with given parameters.
   *
   * @param scan is a scanner object.
   * @param out  is an appendable object.
   */
  public SpaceInfo(Scanner scan, Appendable out) {
    if (scan == null || out == null) {
      throw new IllegalArgumentException("Scanner and Appendable shouldn't be null.");
    }
    this.scan = scan;
    this.out = out;
    this.spaceName = "";
  }

  @Override
  public void playGame(World w) throws IllegalArgumentException {
    if (w == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    try {
      while (true) {
        try {
          this.spaceName = scan.nextLine();
          out.append(w.displaySpaceInfo(this.spaceName));
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
