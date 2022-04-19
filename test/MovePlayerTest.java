import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.controller.commands.MovePlayer;
import game.model.World;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests move player command with various scenarios.
 */
public class MovePlayerTest {
  private World world;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
  }

  @Test
  public void testMovePlayerTest() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("Kitchen");
    Scanner sc = new Scanner(in);
    WorldController wc = new MovePlayer(sc, out);
    wc.playGame(world);
    StringBuilder sb = new StringBuilder();
    sb.append("Inputs:\n"
        + " Space Name:Kitchen\n"
        + "moved to space 1011\n");
    assertEquals(sb.toString(), log.toString());
    sb = new StringBuilder();
    sb.append("Player has been moved to Kitchen\n");
    assertEquals(sb.toString(), out.toString());
  }
}
