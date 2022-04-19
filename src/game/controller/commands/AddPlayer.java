package game.controller.commands;

import game.controller.WorldController;
import game.model.World;
import java.io.IOException;
import java.util.Scanner;

/**
 * This command class implements WorldController interface and adds a player to the world using
 * world methods.
 */
public class AddPlayer implements WorldController {
  private Scanner scan;
  private Appendable out;


  /**
   * This constructor initializes add player object by scanning the inputs and displaying outputs.
   *
   * @param scan scanner input
   * @param out  appendable output
   */
  public AddPlayer(Scanner scan, Appendable out) {
    if (scan == null || out == null) {
      throw new IllegalArgumentException("Scanner and Appendable shouldn't be null.");
    }
    this.scan = scan;
    this.out = out;
  }

  @Override
  public void playGame(World w) throws IllegalArgumentException {
    if (w == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    try {
      while (true) {
        try {
          out.append(
              "Enter name of the player\n");
          String name = scan.nextLine();
          out.append(
              "Enter location of the player\n");
          String location = scan.nextLine();
          w.addPlayer(name, location);
          out.append(
              "**Player " + name + " is added**\n");
          break;
        } catch (IllegalArgumentException ie) {
          out.append(ie.getMessage());
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException(ioe.getMessage());
    }
  }
}
