package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import game.view.View;

/**
 * This command class implements world controller interface and attacks the target with the
 * item by calling world object.
 */
public class Attack implements WorldController {
  private String item;

  /**
   * This is a constructor which initializes an attack object with given parameters.
   *
   * @param item is an item name.
   */
  public Attack(String item) {
    if (item == null || "".equals(item.trim())) {
      throw new IllegalArgumentException("Item shouldn't be empty");
    }
    this.item = item;
  }

  @Override
  public void playGame(World w, View view) throws IllegalArgumentException {
    if (w == null || view == null) {
      throw new IllegalArgumentException("Model and view cannot be null");
    }
    try {
      w.attack(item);
      view.showSuccessMessage("Player Status", "Attacked target successfully.");
    } catch (IllegalStateException ise) {
      view.showErrorMessage(ise.getMessage());
    }
  }
}
