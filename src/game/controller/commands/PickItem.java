package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import game.view.View;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements world controller interface and picks item from a space by calling
 * world object.
 */
public class PickItem implements WorldController {
  private String itemName;

  /**
   * This is a constructor which initializes a pick item object with given parameters.
   *
   */
  public PickItem(String item) {
    if (item==null||"".equals(item.trim())) {
      throw new IllegalArgumentException("Scanner and Appendable shouldn't be null.");
    }
    this.itemName = item;
  }

  @Override
  public void playGame(World w, View v) throws IllegalArgumentException {
      if (w == null) {
        throw new IllegalArgumentException("model cannot be null");
      }
      w.pickItem(this.itemName);
  }
}
