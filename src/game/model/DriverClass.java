package game.model;

import game.controller.Controller;
import game.controller.ExtensibleController;
import game.view.View;
import game.view.WorldView;
import java.io.FileReader;
import java.io.IOException;

/**
 * It takes the input from the user and acts as driver class.
 */
public class DriverClass {
  /**
   * It is a main method of the class.
   *
   * @param args gets the input file
   */
  public static void main(String[] args) {
    try {
      Readable file = new FileReader(args[0]);
      int numOfTurns = Integer.parseInt(args[1]);
      World model = new ConcreteWorld(file, new RandomGen(), numOfTurns);
      Controller c = new ExtensibleController(model, numOfTurns, args[0]);
      View view = new WorldView("Doctor Lucky", model);
      c.execute(view);
    } catch (IOException ioe) {
      System.exit(1);
    }
  }
}
