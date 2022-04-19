package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;

/**
 * This command class implements world controller interface and calls the lookaround method from
 * world-class and returns the description.
 */
public class LookAround implements WorldController {
  private Appendable out;

  /**
   * This constructor initializes the look around object.
   *
   * @param out displays output.
   */
  public LookAround(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Appendable shouldn't be null.");
    }
    this.out = out;
  }

  @Override
  public void playGame(World w) {
    if (w == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    try {
      out.append(w.lookAround());
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed");
    }
  }
}
