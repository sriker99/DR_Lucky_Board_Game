package game.view.panels;

import game.controller.Features;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class implements the welcome screen of the game using JPanel.
 */
public class WelcomeScreen extends JPanel {

  private final long serialVersionUid;
  private JButton startGame;

  /**
   * This constructor initializes the welcome screen panel.
   */
  public WelcomeScreen() {
    this.setLayout(new BorderLayout());
    serialVersionUid = 1L;
    JLabel thumb = new JLabel();
    thumb.setIcon(new ImageIcon(new ImageIcon("res/Splash.jpg").getImage().getScaledInstance(500,
        500, Image.SCALE_DEFAULT)));

    this.add(thumb, BorderLayout.CENTER);
    startGame = new JButton("Play");
    startGame.setBounds(100, 100, 100, 80);
    this.add(startGame, BorderLayout.PAGE_END);
  }

  /**
   * This method implements features present in the welcome screen.
   *
   * @param f is the features object.
   */
  public void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalArgumentException("Features object shouldn't be null.");
    }
    startGame.addActionListener(l -> f.switchToPlayerConfigScreen());
  }
}