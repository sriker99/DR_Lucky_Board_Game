package game.view.panels;

import game.controller.Features;
import game.model.ReadOnlyWorld;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
  public GameScreen(ReadOnlyWorld world) {
    if (world == null) {
      throw new IllegalArgumentException("Read only world object cannot be null");
    }
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    serialVersionUid = 1L;
    Border border = new LineBorder(Color.RED, 4, true);
    this.setBorder(border);
    itemsCombo = new JComboBox();
    sidePanel = new JPanel();
    sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
    try {
      icon = new ImageIcon(world.cropImage());
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Unable to read the file.");
    }
    thumb = new JLabel();
    thumb.setIcon(icon);
    String description = world.displayClues();
    clues = new JLabel(description);
    JLabel rules =
        new JLabel("<html><b>CLUES</b><br/>" + "Click on the space to move the players<br/>"
            + "p to pick item from the space<br/>"
            + "l to look around the player<br/>"
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
    clues.setText("<html>" + temp.replaceAll("\n", "<br/>") + "</html>");
    try {
      thumb.setIcon(new ImageIcon(world.cropImage()));
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read the file");
    }
  }

  public void setFeatures(Features f) {
    graphPanel.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        f.movePlayer(e.getX(), e.getY());
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }
    });
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
          String[] itemOptions = world.getPlayerItems();
          f.displayItemsDialog("Attack with item", itemOptions);
          f.attack(String.valueOf(itemsCombo.getSelectedItem()));
        } else if (e.getKeyChar() == 'l') {
          f.displayLookAround();
        } else if (e.getKeyChar() == 'p') {
          if (world.getSpaceItems().length == 0) {
            f.displayErrorDialog("Space has no items.");
          } else {
            String[] itemOptions = world.getSpaceItems();
            f.displayItemsDialog("Pick the item", itemOptions);
            f.pick(String.valueOf(itemsCombo.getSelectedItem()));
          }
        } else if (e.getKeyChar() == 'm') {
          String[] itemOptions = world.getSpaces();
          f.displayItemsDialog("Move the pet to location", itemOptions);
          f.movePet(String.valueOf(itemsCombo.getSelectedItem()));
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
  }

  @Override
  public void paintComponent(Graphics g) {
    Toolkit t = Toolkit.getDefaultToolkit();
  }

  /**
   * Resets the focus for panel.
   */
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * Dialog box pops with items and title.
   *
   * @param title is the name of the dialog box
   * @param items options in the dialog box.
   */
  public void showItemsDialog(String title, String[] items) {
    if (title == null || "".equals(title.trim()) || items.length == 0) {
      throw new IllegalArgumentException("Title and items shouldn't be empty");
    }
    itemsCombo = new JComboBox<>(items);
    JOptionPane.showMessageDialog(new JFrame(), itemsCombo, title, JOptionPane.QUESTION_MESSAGE);
  }
}

