import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.model.World;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the displaying space information command with different inputs.
 */
public class SpaceInfoTest {
  private World world;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
  }

  @Test
  public void testSpaceInfoTest() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("Armory");
    Scanner sc = new Scanner(in);
    WorldController wc = new SpaceInfo(sc, out);
    wc.playGame(world);
    StringBuilder sb = new StringBuilder();
    sb.append("Input:\n"
        + "  Enter the player space name: Armory\n");
    assertEquals(sb.toString(), log.toString());
  }

}
