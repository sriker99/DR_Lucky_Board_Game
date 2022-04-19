import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.controller.commands.LookAround;
import game.model.World;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests look around command with different scenarios.
 */
public class LookAroundTest {

  private World world;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
  }

  @Test
  public void testLookAroundTest() {
    StringBuffer out = new StringBuffer();
    WorldController wc = new LookAround(out);
    wc.playGame(world);
    StringBuilder sb = new StringBuilder();
    sb.append("Look around is invoked 1011\n");
    assertEquals(sb.toString(), out.toString());
  }
}
