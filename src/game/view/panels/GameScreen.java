package game.view.panels;

import game.model.ReadOnlyWorld;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This class represents the game screen where game is played.
 */
public class GameScreen extends JPanel {
  private static final long serialVersionUID = 1L;
  private JPanel graphPanel;
  private JPanel cluesPanel;
  private JPanel sidePanel;
  private JPanel responsePanel;
  private JPanel rulesPanel;
  private ReadOnlyWorld world;
  private JLabel clues;

  /**
   * This is a constructs initialises the game panel.
   *
   * @param world is the read only world object.
   */
  public GameScreen(ReadOnlyWorld world) {
    if (world == null) {
      throw new IllegalArgumentException("Read only world object cannot be null");
    }
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    Border border = new LineBorder(Color.RED, 4, true);
    this.setBorder(border);
    sidePanel = new JPanel();
    sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
    ImageIcon icon = new ImageIcon("res/output.png");
    JLabel thumb = new JLabel();
    thumb.setIcon(icon);
    String description = world.displayClues();
    JLabel clues = new JLabel(description);
    clues = new JLabel(description);
    JLabel rules = new JLabel("<html>Enter 1 to move the player<br/>"
        + "p to pick item from the space<br/>" + "l to look around the player<br/>"
        + "4 to display player information<br/>" + "5 to space information<br/>"
        + "6 to get world map<br/>" + "a to attack the target<br/>" + "m to move pet</html>");
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

  public void updateClues() {
    String temp = world.displayClues();
    clues.setText(temp);
  }

  @Override
  public void paintComponent(Graphics g) {
    Toolkit t = Toolkit.getDefaultToolkit();
    Image i = t.getImage("res/output.png");
    g.drawImage(i, 120, 100, this);
  }
}
