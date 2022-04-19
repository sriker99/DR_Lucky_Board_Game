package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements world controller interface and attacks the target with the
 * item by calling world object.
 */
public class Attack implements WorldController {
  private Scanner scan;
  private Appendable out;

  /**
   * This is a constructor which initializes an attack object with given parameters.
   *
   * @param scan is a scanner object.
   * @param out  is an appendable object.
   */
  public Attack(Scanner scan, Appendable out) {
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
        throw new IllegalArgumentException("model cannot be null");
      }
      while (true) {
        try {
          w.attack(scan.nextLine());
          out.append("Target is attacked.\n");
          break;
        } catch (IllegalStateException is) {
          out.append(is.getMessage() + "Your turn is exhausted\n");
          break;
        } catch (IllegalArgumentException ie) {
          out.append(ie.getMessage() + "\n" + "Enter the item name again\n");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException(ioe.getMessage());
    }
  }
}
