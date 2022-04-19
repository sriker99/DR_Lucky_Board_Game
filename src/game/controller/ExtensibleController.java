package game.controller;

import game.controller.commands.AddComputerPlayer;
import game.controller.commands.AddPlayer;
import game.controller.commands.Attack;
import game.controller.commands.ConstructWorld;
import game.controller.commands.LookAround;
import game.controller.commands.MovePet;
import game.controller.commands.MovePlayer;
import game.controller.commands.PickItem;
import game.controller.commands.PlayerInfo;
import game.controller.commands.SpaceInfo;
import game.model.World;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

/**
 * This is a controller to perform actions based on user inputs by implementing controller
 * interface.
 */
public class ExtensibleController implements Controller {
  private final Appendable out;
  private final Scanner scan;

  /**
   * This is a constructor which initializes the ExtensibleController object with given parameters.
   *
   * @param in  it is a readable object.
   * @param out it is an appendable object.
   */
  public ExtensibleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    this.scan = new Scanner(in);
  }

  @Override
  public void execute(World w) {
    if (w == null) {
      throw new IllegalArgumentException(
          "Model can't be null and number of turns shouldn't be negative or zero.");
    }
    try {
      Stack<WorldController> commands = new Stack<>();
      Map<Integer, Function<Scanner, WorldController>> knownCommands = new HashMap<>();
      out.append("===============WELCOME TO DR LUCKY'S GAME================\n");
      out.append("Enter total number of players\n");
      int n = Integer.parseInt(scan.nextLine());
      Queue<Integer> turns = new LinkedList<>();
      while (n > 0) {
        WorldController k;
        int c;
        while (true) {
          try {
            out.append(
                "Enter 1 to add a human player\nEnter 2 to add a computer player\n");
            c = Integer.parseInt(scan.nextLine());
            if (c == 1) {
              turns.add(1);
              knownCommands.put(1, s -> new AddPlayer(this.scan, out));
              n--;
            } else if (c == 2) {
              turns.add(0);
              knownCommands.put(2, s -> new AddComputerPlayer(out));
              n--;
            }
            Function<Scanner, WorldController> cmd =
                knownCommands.getOrDefault(c, null);
            if (cmd == null) {
              throw new IllegalArgumentException();
            } else {
              k = cmd.apply(scan);
              commands.add(k);
              k.playGame(w);
              break;
            }
          } catch (IllegalArgumentException ie) {
            out.append("Enter valid input\n");
          }
        }
      }
      while (!w.isGameOver()) {
        if (turns.peek() == 1) {
          while (true) {
            try {
              out.append(w.displayClues());
              out.append(
                  "Enter 1 to move the player\n2 to pick item from the space"
                      + " \n3 to look around the player\n4 to "
                      + "display player information\n5 to space information\n6 to get world map\n"
                      + "7 to attack the target\n" + "8 to move pet\n" + "q to exit the game\n");
              knownCommands.put(1, s -> new MovePlayer(this.scan, out));
              knownCommands.put(2, s -> new PickItem(this.scan, out));
              knownCommands.put(3, s -> new LookAround(out));
              knownCommands.put(4, s -> new PlayerInfo(this.scan, out));
              knownCommands.put(5, s -> new SpaceInfo(this.scan, out));
              knownCommands.put(6, s -> new ConstructWorld());
              knownCommands.put(7, s -> new Attack(this.scan, out));
              knownCommands.put(8, s -> new MovePet(this.scan, out));
              WorldController c;
              String in = scan.nextLine();
              if ("q".equalsIgnoreCase(in) || "quit".equalsIgnoreCase(in)) {
                out.append("======GAME QUIT=======");
                out.append("======GAME OVER=======");
                return;
              } else if (Integer.parseInt(in) == 1) {
                out.append("Hi " + w.getTurn() + ", enter the next location to move \n");
              } else if (Integer.parseInt(in) == 2) {
                out.append(
                    "Hi " + w.getTurn()
                        + ", enter the item to be picked  from the current space.\n");
              } else if (Integer.parseInt(in) == 4) {
                out.append("Enter the player index to be displayed.\n");
              } else if (Integer.parseInt(in) == 5) {
                out.append("Enter the space name to be displayed.\n");
              } else if (Integer.parseInt(in) == 6) {
                out.append("World map is generated.\n");
              } else if (Integer.parseInt(in) == 7) {
                out.append("Enter the item name if you have one else click Enter.\n");
              } else if (Integer.parseInt(in) == 8) {
                out.append("Enter the space name\n");
              }
              Function<Scanner, WorldController> cmd =
                  knownCommands.getOrDefault(Integer.parseInt(in), null);
              if (cmd == null) {
                throw new IllegalArgumentException();
              } else {
                c = cmd.apply(scan);
                commands.add(c);
                c.playGame(w);
              }
              break;
            } catch (IllegalArgumentException ill) {
              if (ill.getMessage() == null) {
                out.append("Enter the valid input\n");
              } else {
                out.append(ill.getMessage() + "\n");
              }
            }
          }
          turns.remove();
          turns.add(1);
        } else {
          out.append(w.checkComputerPlayer());
          turns.remove();
          turns.add(0);
        }
      }
      if (w.isGameOver() && w.getWinner() == null) {
        out.append("======Turns exhausted=======");
        out.append("======<DR LUCKY WON THE GAME>=======");
        out.append("========GAME OVER=========");
      } else if (w.getWinner() != null) {
        out.append("============<Player " + w.getWinner() + " won the game>============");
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}
