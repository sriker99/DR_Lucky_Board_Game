import static org.junit.Assert.assertEquals;

import game.controller.Controller;
import game.controller.ExtensibleController;
import game.model.ConcreteWorld;
import game.model.World;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import mockmodel.WorldMock;
import org.junit.Test;

/**
 * This class tests the extensible controller with different types of players and complete flow.
 */
public class ExtensibleControllerTest {
  private StringBuilder input;
  private StringBuilder output;
  private StringBuilder log;
  private StringBuffer out;
  private Controller control;
  private Controller control1;
  private World model;
  private World model1;


  @Test
  public void testTwoHumanPlayers() throws IOException {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("2\n1\nravi\nKitchen\n1\nteja\nArmory\n3\n2\nRevolver\n3\n3\n3\n");
    output
        .append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player teja is added**\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Look around is invoked 6735993\n");
    output.append("============<Player Player 6735993\n");
    output.append(" won the game>============");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Kitchen with 6735993\n");
    sb.append("adding player teja in space Armory with 6735993\n");
    assertEquals(output.toString(), out.toString());
    assertEquals(sb.toString(), log.toString());
  }

  @Test
  public void testInvalidValues() throws IOException {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("2\n3\n1\nravi\nKitchen\n1\nteja\nArmory\n7\nRevolver\n");
    output.append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter valid input\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player teja is added**\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Enter the item name if you have one else click Enter.\n");
    output.append("Target is attacked.\n");
    output.append("============<Player Player 6735993\n");
    output.append(" won the game>============");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Kitchen with 6735993\n");
    sb.append("adding player teja in space Armory with 6735993\n");
    sb.append("Input:\n"
        + "  Enter the item name: Revolver\n"
        + "Target has been attacked 6735993\n");
    assertEquals(output.toString(), out.toString());
    assertEquals(sb.toString(), log.toString());
  }

  @Test
  public void testGameOver() throws IOException {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("2\n1\nravi\nKitchen\n2\nq\n");
    output
        .append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Computer player is added.\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("======GAME QUIT=============GAME OVER=======");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    assertEquals(output.toString(), out.toString());
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Kitchen with 6735993\n");
    sb.append("adding computer player ravi in space Dining Hall with 6735993\n");
    assertEquals(sb.toString(), log.toString());
  }

  @Test
  public void testRandomNumber() throws IOException {
    Reader in;
    in = new FileReader("res/mansion.txt");
    StringBuilder out = new StringBuilder();
    model = new ConcreteWorld(in, new RandomMock(out, 12341, 5, 0, 1, 2), 5);
    model.addComputerPlayer();
    assertEquals(model.checkComputerPlayer(), "Computer player computer_0 has been moved.\n");
  }

  @Test
  public void testAttack() {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("1\n1\nravi\nArmory\n7\n \n");
    output.append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Enter the item name if you have one else click Enter.\n");
    output.append("Target is attacked.\n");
    output.append("============<Player Player 6735993\n");
    output.append(" won the game>============");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Armory with 6735993\n");
    sb.append("Input:\n"
        + "  Enter the item name:  \n"
        + "Target has been attacked 6735993\n");
    assertEquals(output.toString(), out.toString());
    assertEquals(sb.toString(), log.toString());
  }

  @Test
  public void testMovePet() {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("1\n1\nravi\nArmory\n8\nKitchen\n");
    output.append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Enter the space name\n");
    output.append("Pet has been moved \n");
    output.append("============<Player Player 6735993\n");
    output.append(" won the game>============");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Armory with 6735993\n");
    sb.append("Input:\n"
        + "  Enter the space name: Kitchen\n"
        + "Moving pet 6735993\n");
    assertEquals(output.toString(), out.toString());
    assertEquals(sb.toString(), log.toString());
  }

  @Test
  public void testLookAround() {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("1\n1\nravi\nArmory\n3\n");
    output.append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Look around is invoked 6735993\n");
    output.append("============<Player Player 6735993\n");
    output.append(" won the game>============");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Armory with 6735993\n");
    assertEquals(output.toString(), out.toString());
    assertEquals(sb.toString(), log.toString());
  }

  @Test
  public void testMovePlayer() {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("1\n1\nravi\nArmory\n1\nBilliard Room");
    output.append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Hi get turn 6735993\n");
    output.append(", enter the next location to move \n");
    output.append("Player has been moved to Billiard Room\n");
    output.append("============<Player Player 6735993\n");
    output.append(" won the game>============");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Armory with 6735993\n");
    sb.append("Inputs:\n"
        + " Space Name:Kitchen\n"
        + "moved to space 6735993\n");
    assertEquals(output.toString(), out.toString());
    assertEquals(sb.toString(), log.toString());
  }

  @Test
  public void testPickItem() {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("1\n1\nravi\nArmory\n2\nRevolver");
    output.append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Hi get turn 6735993\n");
    output.append(", enter the item to be picked  from the current space.\n");
    output.append("Item has been picked\n");
    output.append("============<Player Player 6735993\n");
    output.append(" won the game>============");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Armory with 6735993\n");
    sb.append("Inputs:\n"
        + " Item name:Revolver\n"
        + "  picked item 6735993\n");
    assertEquals(output.toString(), out.toString());
    assertEquals(sb.toString(), log.toString());
  }

  @Test
  public void testDisplayInfo() {
    input = new StringBuilder();
    output = new StringBuilder();
    log = new StringBuilder();
    model1 = new WorldMock(log, 6735993);
    out = new StringBuffer();
    input.append("1\n1\nravi\nArmory\n4\n0\n5\nArmory\n1\nBilliard Room");
    output.append("===============WELCOME TO DR LUCKY'S GAME================\n");
    output.append("Enter total number of players\n");
    output.append("Enter 1 to add a human player\n");
    output.append("Enter 2 to add a computer player\n");
    output.append("Enter name of the player\n");
    output.append("Enter location of the player\n");
    output.append("**Player ravi is added**\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Enter the player index to be displayed.\n");
    output.append("display player info 6735993\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Enter the space name to be displayed.\n");
    output.append("display space info 6735993\n");
    output.append("Displaying clues 6735993\n");
    output.append("Enter 1 to move the player\n");
    output.append("2 to pick item from the space \n");
    output.append("3 to look around the player\n");
    output.append("4 to display player information\n");
    output.append("5 to space information\n");
    output.append("6 to get world map\n");
    output.append("7 to attack the target\n");
    output.append("8 to move pet\n");
    output.append("q to exit the game\n");
    output.append("Hi get turn 6735993\n");
    output.append(", enter the next location to move \n");
    output.append("Player has been moved to Billiard Room\n");
    output.append("============<Player Player 6735993\n");
    output.append(" won the game>============");
    Readable in = new StringReader(input.toString());
    control1 = new ExtensibleController(in, out);
    control1.execute(model1);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Armory with 6735993\n");
    sb.append("Input:\n");
    sb.append("  Enter the player index: 0\n");
    sb.append("Input:\n");
    sb.append("  Enter the player space name: Armory\n");
    sb.append("Inputs:\n");
    sb.append(" Space Name:Kitchen\n");
    sb.append("moved to space 6735993\n");
    assertEquals(output.toString(), out.toString());
    assertEquals(sb.toString(), log.toString());
  }
}
