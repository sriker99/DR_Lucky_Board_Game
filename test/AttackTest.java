import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.controller.commands.Attack;
import game.model.World;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests attack item command with various inputs.
 */
public class AttackTest {
  private World world;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
  }

  @Test
  public void testAttackTest() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("Gun");
    Scanner sc = new Scanner(in);
    WorldController wc = new Attack(sc, out);
    wc.playGame(world);
    StringBuilder sb = new StringBuilder();
    sb.append("Input:\n"
        + "  Enter the item name: Gun\n"
        + "Target has been attacked 1011\n");
    assertEquals(sb.toString(), log.toString());
    sb = new StringBuilder();
    sb.append("Target is attacked.\n");
    assertEquals(sb.toString(), out.toString());
  }
}
