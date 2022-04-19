package game.model;

import game.controller.Controller;
import game.controller.ExtensibleController;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      Controller c = new ExtensibleController(input, output);
      c.execute(new ConcreteWorld(file, new RandomGen(), numOfTurns));
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
      System.exit(1);
    }
  }
}
