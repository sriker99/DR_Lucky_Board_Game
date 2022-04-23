package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import game.view.View;
import java.io.IOException;
import java.util.Scanner;

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
      if (w == null) {
        throw new IllegalArgumentException("model cannot be null");
      }
      w.attack(item);
  }
}
