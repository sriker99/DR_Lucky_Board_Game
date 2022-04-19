import static org.junit.Assert.assertEquals;

import game.model.Pet;
import game.model.PetImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the pet present in the world.
 */
public class PetImplTest {

  private Pet pet;

  /**
   * Initializing the objects with required values and performing operations before testing is done.
   */
  @Before
  public void setUp() {
    pet = new PetImpl("Turtle", 0);
  }

  @Test
  public void getPetName() {
    assertEquals("Turtle", pet.getPetName());
  }

  @Test
  public void getPetLocation() {
    assertEquals(0, pet.getPetLocation());
  }

  @Test
  public void setPetLocation() {
    pet.setPetLocation(10);
    assertEquals(10, pet.getPetLocation());
  }

}