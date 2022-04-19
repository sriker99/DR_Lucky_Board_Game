import static org.junit.Assert.assertEquals;

import game.model.Item;
import game.model.Player;
import game.model.PlayerImpl;
import game.model.Space;
import game.model.SpaceImpl;
import game.model.Thing;
import game.model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test SpaceImpl class with all necessary actions.
 */
public class SpaceImplTest {

  private Space space1;
  private Thing thing;
  private Player player;

  /**
   * This method initializes the objects with required values before a test executes.
   */
  @Before
  public void setup() {
    space1 = new SpaceImpl(2, 4, 8, 10, "kitchen");
    thing = new Item("Gun", 5, 0);
    player = new PlayerImpl("ravi", 0, 0, User.HUMAN);
    space1.addPlayersInSpace("ravi");
    space1.setItems("Gun");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidNegativeValues() {
    Space space2 = new SpaceImpl(-2, 4, 5, 10, "trophy");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmptyName() {
    Space space2 = new SpaceImpl(2, 4, 5, 10, "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidValues() {
    Space space2 = new SpaceImpl(7, 4, 5, 10, "");
  }

  @Test
  public void getLowerRightCol() {
    assertEquals(10, space1.getLowerRightCol());
  }

  @Test
  public void getLowerRightRow() {
    assertEquals(8, space1.getLowerRightRow());
  }

  @Test
  public void getUpperLeftCol() {
    assertEquals(4, space1.getUpperLeftCol());
  }

  @Test
  public void getUpperLeftRow() {
    assertEquals(2, space1.getUpperLeftRow());
  }

  @Test
  public void getNameOfSpace() {
    assertEquals("kitchen", space1.getNameOfSpace());
  }

  @Test
  public void removeItem() {
    assertEquals(false, space1.getItems().isEmpty());
    space1.removeItem(thing.getName());
    assertEquals(true, space1.getItems().isEmpty());
  }

  @Test
  public void getPlayersInSpace() {
    assertEquals("ravi", space1.getPlayersInSpace().get(0));
  }

  @Test
  public void removePlayersInSpace() {
    space1.removePlayerFromSpace(player.getPlayerName());
    assertEquals(true, space1.getPlayersInSpace().isEmpty());
  }
}