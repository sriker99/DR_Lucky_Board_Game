import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.controller.commands.MovePet;
import game.model.World;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests move pet command with various scenarios.
 */
public class MovePetTest {
  private World world;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
  }

  @Test
  public void testMovePetTest() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("Kitchen");
    Scanner sc = new Scanner(in);
    WorldController wc = new MovePet(sc, out);
    wc.playGame(world);
    StringBuilder sb = new StringBuilder();
    sb.append("Input:\n"
        + "  Enter the space name: Kitchen\n"
        + "Moving pet 1011\n");
    assertEquals(sb.toString(), log.toString());
    sb = new StringBuilder();
    sb.append("Pet has been moved \n");
    assertEquals(sb.toString(), out.toString());
  }
}
