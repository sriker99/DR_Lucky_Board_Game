package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;

/**
 * This command class implements WorldController interface and adds a computer player to the world
 * using world methods.
 */
public class AddComputerPlayer implements WorldController {




  @Override
  public void playGame(World w) throws IllegalArgumentException {
    if (w == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

      w.addComputerPlayer();

  }
}
