package game.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ReadOnlyWorld {
  String[] getSpaces();

  String displayClues();

  String[] getPlayerItems();

  String lookAround();

  String[] getSpaceItems();

  /**
   * Inserts players and target on the map.
   * @return a processed image
   */
  public BufferedImage cropImage() throws IOException;
}
