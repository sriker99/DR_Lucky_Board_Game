package game.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This interfaces contains the method signatures for readonly that are accessed by the view.
 */
public interface ReadOnlyWorld {

  /**
   * Access the spaces present in the world.
   *
   * @return list of space names.
   */
  String[] getSpaces();

  /**
   * Displaying clues before every turn.
   *
   * @return the clues.
   */
  String displayClues();

  /**
   * Access the items present with player.
   *
   * @return the list of items.
   */
  String[] getPlayerItems();

  /**
   * Access the look around of the current player.
   *
   * @return the string.
   */
  String lookAround();

  /**
   * Access the items present in the given space.
   *
   * @return the list of the items.
   */
  String[] getSpaceItems();

  /**
   * Inserts players and target on the map.
   *
   * @return a processed image
   */
  public BufferedImage cropImage() throws IOException;
}
