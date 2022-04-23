package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import game.view.View;
import java.io.IOException;

/**
 * This command class implements world controller interface and calls the lookaround method from
 * world-class and returns the description.
 */
public class LookAround implements WorldController {
  private String response;

  public LookAround(){
    this.response="";
  }

  @Override
  public void playGame(World w, View view) {
    if (w == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
     response= w.lookAround();
    view.showSuccessMessage("Look Around",response);
  }

  public String getResponse(){
    return response;
  }
}
