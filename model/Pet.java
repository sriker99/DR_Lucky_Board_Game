package game.model;

/**
 * This interface contains method signatures of pet class which is created when a target is created
 * in the world.
 */
public interface Pet {

  /**
   * This method finds the name of the pet.
   *
   * @return the name of the pet.
   */
  String getPetName();

  /**
   * This method finds the location of the pet in the world.
   *
   * @return the location of the pet.
   */
  int getPetLocation();

  /**
   * This method sets the location of the pet in the world.
   *
   * @param location of the pet.
   */
  void setPetLocation(int location);
}
