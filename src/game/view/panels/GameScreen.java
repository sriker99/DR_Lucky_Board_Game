package game.view.panels;

import game.model.ReadOnlyWorld;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This class represents the game screen where game is played.
 */
public class GameScreen extends JPanel {
  private final long serialVersionUid;
  private JPanel graphPanel;
  private JPanel cluesPanel;
  private JPanel sidePanel;
  private JPanel responsePanel;
  private JPanel rulesPanel;
  private ReadOnlyWorld world;
  private JLabel clues;
  private ImageIcon icon;
  private JLabel thumb;
  private JComboBox itemsCombo;

  /**
   * This is a constructs initialises the game panel.
   *
   * @param world is the read only world object.
   */
  public GameScreen(ReadOnlyWorld world) throws IOException {
    if (world == null) {
      throw new IllegalArgumentException("Read only world object cannot be null");
    }
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    serialVersionUid = 1L;
    Border border = new LineBorder(Color.RED, 4, true);
    this.setBorder(border);
    sidePanel = new JPanel();
    sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
    icon = new ImageIcon(world.cropImage());
    thumb = new JLabel();
    thumb.setIcon(icon);
    String description = world.displayClues();
    clues = new JLabel(description);
    JLabel rules =
        new JLabel("<html>p to pick item from the space<br/>" + "l to look around the player<br/>"
            + "a to attack the target<br/>" + "m to move pet</html>");
    this.world = world;
    graphPanel = new JPanel();
    cluesPanel = new JPanel();
    responsePanel = new JPanel();
    rulesPanel = new JPanel();
    graphPanel.add(thumb);
    this.add(graphPanel);
    cluesPanel.add(clues);
    sidePanel.add(cluesPanel);
    sidePanel.add(responsePanel);
    rulesPanel.add(rules);
    sidePanel.add(rulesPanel);
    this.add(sidePanel);
  }

  /**
   * Updates clues after every turn.
   */
  public void updateClues() {
    String temp = world.displayClues();
    clues.setText(temp);
    try {
      thumb.setIcon(new ImageIcon(world.cropImage()));
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read the file");
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    Toolkit t = Toolkit.getDefaultToolkit();
  }

}

