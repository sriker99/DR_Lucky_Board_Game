import static org.junit.Assert.assertEquals;

import game.controller.WorldController;
import game.controller.commands.AddPlayer;
import game.model.World;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import mockmodel.WorldMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the add player command.
 */
public class AddPlayerTest {
  private World world;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    world = new WorldMock(log, 1011);
  }

  @Test
  public void testAddPlayerTest() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("ravi\nKitchen\n");
    Scanner sc = new Scanner(in);
    WorldController wc = new AddPlayer(sc, out);
    wc.playGame(world);
    StringBuilder sb = new StringBuilder();
    sb.append("adding player ravi in space Kitchen with 1011\n");
    assertEquals(sb.toString(), log.toString());
    sb = new StringBuilder();
    sb.append("Enter name of the player\n"
        + "Enter location of the player\n"
        + "**Player ravi is added**\n");
    assertEquals(sb.toString(), out.toString());
  }
}
