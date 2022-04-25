import static org.junit.Assert.assertEquals;

import game.controller.ExtensibleController;
import game.controller.Features;
import game.view.View;
import mockmodel.ViewMock;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test controller.
 */
public class ExtensibleControllerTest {

  private StringBuilder sb;
  private WorldMock model;
  private ExtensibleController controller;
  private View view;
  private Features f;

  /**
   * Initializes the mock models.
   */
  @Before
  public void setUp() {
    sb = new StringBuilder();
    model = new WorldMock(sb, 3000);
    view = new ViewMock(sb, 4000);
    controller = new ExtensibleController(model, 5, "res/mansion.txt");
  }

  @Test
  public void startNewGameTest() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.startGame();
    assertEquals(
        "set features method is invoked\n"
            + "unique code 4000\n"
            + "resetFocus method is invoked\n"
            + "unique code 4000\n"
            + "disposeFrame method is invoked\n"
            + "unique code 4000\n",
        sb.toString());
  }

  @Test
  public void testExitProgram() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.exitProgram();
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Game Status Game has ended successfully\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n", sb.toString());
  }


  @Test
  public void testAddPlayer() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.addPlayer("COMPUTER", "Sriker,RAVI", "Armory");
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "adding computer player ravi in space Dining Hall with 3000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Player Status Computer player is added to the world\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testSwitchToWelcomeScreen() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.switchToWelcomeScreen();
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "changeToWelcomeScreen method is invoked\n"
        + "unique code 4000\n", sb.toString());
  }


  @Test
  public void testSwitchToPlayerConfigScreen() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.switchToGameScreen();
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs message:There should be minimum 2 players to start the game\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testSwitchToGameScreen() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.switchToGameScreen();
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs message:There should be minimum 2 players to start the game\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testplayGameWithUploadedFile() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.playGameWithUploadedFile("res.txt");
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs message:res.txt (No such file or directory)\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testAttack() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.attack("Gun");
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "Input:\n"
        + "  Enter the item name: Gun\n"
        + "Target has been attacked 3000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Player Status Attacked target successfully.\n"
        + "unique code 4000\n"
        + "updateClues method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: GAME IS OVER Player Player 3000\n"
        + " won the game\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Game Status Game has ended successfully\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testPick() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.pick("Gun");
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "Inputs:\n"
        + " Item name:Gun\n"
        + "  picked item 3000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Player Status Picked item successfully.\n"
        + "unique code 4000\n"
        + "updateClues method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: GAME IS OVER Player Player 3000\n"
        + " won the game\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Game Status Game has ended successfully\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testMovePet() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.movePet("Armory");
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "Input:\n"
        + "  Enter the space name: Armory\n"
        + "Moving pet 3000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Pet Status Pet has been moved.\n"
        + "unique code 4000\n"
        + "updateClues method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: GAME IS OVER Player Player 3000\n"
        + " won the game\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Game Status Game has ended successfully\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testMovePlayer() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.movePlayer(5, 3);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "move Player method is invoked\n"
        + "Inputs: x and y : 5 3 \n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Player Status unique code 3000\n"
        + "\n"
        + "unique code 4000\n"
        + "updateClues method is invoked\n"
        + "unique code 4000\n"
        + "get player turn method is invoked\n", sb.toString());
  }

  @Test
  public void testDisplayItemsDialog() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    String[] items = {"ITEM"};
    controller.displayItemsDialog("Items", items);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "showItemsDialog method is invoked\n"
        + "Inputs title and items: Items [Ljava.lang.String;@42f93a98\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testErrorDialog() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.displayErrorDialog("Error");
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs message:Error\n"
        + "unique code 4000\n", sb.toString());
  }

  @Test
  public void testDisplayLookAround() {
    controller.execute(view);
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n", sb.toString());
    controller.displayLookAround();
    assertEquals("set features method is invoked\n"
        + "unique code 4000\n"
        + "resetFocus method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Look Around Look around is invoked 3000\n"
        + "\n"
        + "unique code 4000\n"
        + "updateClues method is invoked\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: GAME IS OVER Player Player 3000\n"
        + " won the game\n"
        + "unique code 4000\n"
        + "showSuccessMessage method is invoked\n"
        + "Inputs title and message: Game Status Game has ended successfully\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n"
        + "disposeFrame method is invoked\n"
        + "unique code 4000\n", sb.toString());
  }


}
