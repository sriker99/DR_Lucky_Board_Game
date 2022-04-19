import static org.junit.Assert.assertEquals;

import game.model.Player;
import game.model.PlayerImpl;
import game.model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the player class.
 */
public class PlayerImplTest {

  private Player player;

  /**
   * Initializing the objects with required values and performing operations before testing is done.
   */
  @Before
  public void setUp() {
    player = new PlayerImpl("ravi", 0, 0, User.HUMAN);
    player.addItem("Revolver");
  }

  @Test
  public void getCurrentSpaceIndex() {
    assertEquals(0, player.getCurrentSpaceIndex());
  }

  @Test
  public void setCurrentSpaceIndex() {
    player.setCurrentSpaceIndex(4);
    assertEquals(4, player.getCurrentSpaceIndex());
  }

  @Test
  public void getItems() {
    assertEquals("Revolver", player.getItems().get(0));
  }

  @Test
  public void getPlayerIndex() {
    assertEquals(1, player.getPlayerIndex());
  }

  @Test
  public void getItemLimit() {
    assertEquals(2, player.getItemLimit());
  }

  @Test
  public void testToString() {
    assertEquals("Player name is ravi, and its location is 0,  carrying items "
        + "[Revolver]\n", player.toString());
  }

}