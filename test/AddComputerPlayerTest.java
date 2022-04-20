import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.controller.commands.AddComputerPlayer;
import game.model.World;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests add computer player command.
 */
public class AddComputerPlayerTest {
  private World world;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
  }

  @Test
  public void testAddComputerPlayerTest() {
    WorldController wc = new AddComputerPlayer();
    wc.playGame(world);
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("adding computer player ravi in space Dining Hall with 1011\n"));
    sb.append("Computer player is added.\n");
    assertEquals(sb.toString(), log.toString());
  }
}
