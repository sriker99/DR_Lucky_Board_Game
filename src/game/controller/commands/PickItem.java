package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements world controller interface and picks item from a space by calling
 * world object.
 */
public class PickItem implements WorldController {
  private String itemName;
  private Scanner scan;
  private Appendable out;

  /**
   * This is a constructor which initializes a pick item object with given parameters.
   *
   * @param scan is a scanner object.
   * @param out  is an appendable object.
   */
  public PickItem(Scanner scan, Appendable out) {
    if (scan == null || out == null) {
      throw new IllegalArgumentException("Scanner and Appendable shouldn't be null.");
    }
    this.scan = scan;
    this.out = out;
    this.itemName = "";
  }

  @Override
  public void playGame(World w) throws IllegalArgumentException {
    try {
      if (w == null) {
        throw new IllegalArgumentException("model cannot be null");
      }
      while (true) {
        try {
          this.itemName = scan.nextLine();
          w.pickItem(this.itemName);
          out.append("Item has been picked\n");
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
