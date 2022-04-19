import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.controller.commands.PlayerInfo;
import game.model.World;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the player info command with various inputs.
 */
public class PlayerInfoTest {
  private World world;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
  }

  @Test
  public void testPlayerInfoTest() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("1");
    Scanner sc = new Scanner(in);
    int i = 0;
    WorldController wc = new PlayerInfo(sc, out);
    wc.playGame(world);
    StringBuilder sb = new StringBuilder();
    sb.append("Input:\n"
        + "  Enter the player index: 1\n");
    assertEquals(sb.toString(), log.toString());
    sb = new StringBuilder();
    sb.append("display player info 1011\n");
    assertEquals(sb.toString(), out.toString());
  }

}