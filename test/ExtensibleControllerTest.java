import static org.junit.Assert.assertEquals;

import game.controller.Controller;
import game.controller.ExtensibleController;
import game.model.World;
import game.view.View;
import mockmodel.ViewMockModel;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test controller.
 */
public class ExtensibleControllerTest {

  private StringBuilder sb;
  private World model;
  private Controller controller;
  private View view;

  /**
   * Initializes the mock models;
   */
  @Before
  public void setUp() {
    sb = new StringBuilder();
    model = new WorldMock(sb, 3000);
    view = new ViewMockModel(sb, 4000);
    controller = new ExtensibleController(model, 5, "res/mansion.txt");
  }

  @Test

  public void startNewGameTest() {
    controller.execute(view);
    assertEquals("makeWelcomePanelVisible invoked\n" + "Unique Code: 5500\n"

        + "setFeatures invoked\n" + "Unique Code: 5500\n" + "", sb.toString());

//    controller.startNewGame();
//
//    assertEquals(
//
//        "makeWelcomePanelVisible invoked\n" + "Unique Code: 5500\n" + "setFeatures invoked\n"
//
//            + "Unique Code: 5500\n" + "startNewGame invoked\n" + "Unique Code: 5500\n" + "",
//
//        sbuilder.toString());

  }

}
