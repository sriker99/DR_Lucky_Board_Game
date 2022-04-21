package game.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.controller.Features;

/**
 * This class implements the welcome screen of the game using JPanel.
 */
public class WelcomeScreen extends JPanel {

  private static final long serialVersionUID = 1L;
  private JButton startGame;

  public WelcomeScreen() {
    this.setLayout(new BorderLayout());
    ImageIcon icon = new ImageIcon("res/Welcome.jpg");
    JLabel thumb = new JLabel();
    thumb.setIcon(icon);
    this.add(thumb, BorderLayout.CENTER);
    startGame = new JButton("Play");
    startGame.setBounds(100, 100, 100, 80);
    this.add(startGame, BorderLayout.PAGE_END);
  }

  public void setFeatures(Features f) {
    startGame.addActionListener(l -> f.switchToPlayerConfigScreen());
  }
}