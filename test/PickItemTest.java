import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.controller.commands.PickItem;
import game.model.World;
import game.view.View;
import mockmodel.ViewMock;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests pick item command with various inputs.
 */
public class PickItemTest {
  private World world;
  private StringBuilder log;
  private View view;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
    view = new ViewMock(log, 1011);
  }

  @Test
  public void testPickItemTest() {
    StringBuffer out = new StringBuffer();
    WorldController wc = new PickItem("Gun");
    wc.playGame(world, view);
    StringBuilder sb = new StringBuilder();
    sb.append("Inputs:\n"
        + " Item name:Gun\n"
        + "  picked item 1011\n");
    assertEquals(sb.toString(), log.toString());
    sb = new StringBuilder();
    sb.append("Item has been picked\n");
    assertEquals(sb.toString(), out.toString());
  }
}
