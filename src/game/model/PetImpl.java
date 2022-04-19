package game.model;

/**
 * This class represents pet and implements pet interface.
 */
public class PetImpl implements Pet {

  private String petName;
  private int location;

  /**
   * This is a constructor which initializes a player object with given parameters.
   *
   * @param petName  name of the pet.
   * @param location of the pet.
   */
  public PetImpl(String petName, int location) {
    if (petName == null || "".equals(petName.trim()) || location < 0) {
      throw new IllegalArgumentException("Pet name or location are invalid.");
    }
    this.petName = petName;
    this.location = location;
  }

  @Override
  public String getPetName() {
    return this.petName;
  }

  @Override
  public int getPetLocation() {
    return this.location;
  }

  @Override
  public void setPetLocation(int location) {
    if (location < 0) {
      throw new IllegalArgumentException("Location is invalid");
    }
    this.location = location;
  }
}
