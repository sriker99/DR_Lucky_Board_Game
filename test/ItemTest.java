import static org.junit.Assert.assertEquals;

import game.model.Item;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test Item class with all necessary actions.
 */
public class ItemTest {

  private Item i1;

  @Before
  public void setup() {
    i1 = new Item("knife", 10, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidValues() {
    Item i2 = new Item("Gun", -10, 0);
  }

  @Test
  public void getDamage() {
    assertEquals(10, i1.getDamage());
  }

  @Test
  public void getName() {
    assertEquals("knife", i1.getName());
  }

  @Test
  public void getIndexOfRoom() {
    assertEquals(2, i1.getIndexOfRoom());
  }
}