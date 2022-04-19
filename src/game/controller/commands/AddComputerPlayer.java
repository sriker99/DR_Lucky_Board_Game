package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;

/**
 * This command class implements WorldController interface and adds a computer player to the world
 * using world methods.
 */
public class AddComputerPlayer implements WorldController {
  private Appendable out;

  /**
   * This constructor initializes the add computer player object and displays outputs based on the
   * results.
   *
   * @param out displays output.
   */
  public AddComputerPlayer(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Appendable shouldn't be null.");
    }
    this.out = out;
  }

  @Override
  public void playGame(World w) throws IllegalArgumentException {
    if (w == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    try {
      w.addComputerPlayer();
      out.append("Computer player is added.\n");
    } catch (IOException ioe) {
      throw new IllegalStateException(ioe.getMessage());
    }
  }
}
