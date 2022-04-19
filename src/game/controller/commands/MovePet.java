package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements world controller interface and moves the player to next space given
 * by calling world object.
 */
public class MovePet implements WorldController {
  private Scanner scan;
  private Appendable out;

  /**
   * This constructor initializes move pet object with the given inputs.
   *
   * @param scan gets input from the user.
   * @param out  displays output.
   */
  public MovePet(Scanner scan, Appendable out) {
    if (scan == null || out == null) {
      throw new IllegalArgumentException("Scanner and Appendable shouldn't be null.");
    }
    this.scan = scan;
    this.out = out;
  }

  @Override
  public void playGame(World w) throws IllegalArgumentException {
    try {
      if (w == null) {
        throw new IllegalArgumentException("Model cannot be null");
      }
      while (true) {
        try {
          w.movePet(scan.nextLine());
          out.append(String.format("Pet has been moved \n"));
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
