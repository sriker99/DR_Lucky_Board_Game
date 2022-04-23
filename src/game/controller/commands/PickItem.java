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
   * @param item is item to be picked from the space.
   */
  public PickItem(String item) {
    if (item==null||"".equals(item.trim())) {
      throw new IllegalArgumentException("Scanner and Appendable shouldn't be null.");
    }
    this.itemName = item;
  }

  @Override
  public void playGame(World w, View v) throws IllegalArgumentException {
      if (w == null || v == null) {
        throw new IllegalArgumentException("Model and view cannot be null");
      }
      try{
      w.pickItem(this.itemName);
      v.showSuccessMessage("Player Status","Picked item successfully.");
      }catch (IllegalStateException ise){
      v.showErrorMessage(ise.getMessage());
  }
  }
}
