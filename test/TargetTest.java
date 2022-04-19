import static org.junit.Assert.assertEquals;

import game.model.Target;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test target class with all necessary actions.
 */
public class TargetTest {

  private Target target1;
  private Target target2;

  /**
   * Initializing the target.
   */
  @Before
  public void setup() throws FileNotFoundException {
    target1 = new Target("lucky", 50, 0);
    target2 = new Target("killbill", 60, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidValues() {
    Target target3 = new Target("user", -1, 0);
  }

  @Test
  public void getHealth() {
    assertEquals(50, target1.getHealth());
    assertEquals(60, target2.getHealth());
  }

  @Test
  public void getCurrentSpaceIndex() {
    assertEquals(0, target1.getCurrentSpaceIndex());
  }


  @Test
  public void testToString() {
    assertEquals("Target name is lucky, health is 50, current location is 0\n",
        target1.toString());
  }
}