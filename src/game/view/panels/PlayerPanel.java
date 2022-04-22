package game.view.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.controller.Features;
import game.model.ReadOnlyWorld;

public class PlayerPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private final static String humanPlayerPanel = "HUMAN";
  private final static String computerPlayerPanel = "COMPUTER";
  private JPanel playerType;
  private JPanel humanPlayer;
  private JPanel humanPlayerLabels;
  private JPanel humanPlayerEntries;
  private JPanel buttonsPanel;
  private JLabel playerTypeLabel;
  private JComboBox<String> playerTypeComboBox;
  private String[] playerTypeComboBoxItems = { humanPlayerPanel, computerPlayerPanel };
  private JLabel playerNameLabel;
  private JLabel playerLocationLabel;
  private JTextArea playerNameTextArea;
  private JComboBox<String> playerLocationComboBox;
  private JButton addPlayer;
  private JButton startGame;

  public PlayerPanel(ReadOnlyWorld world) {
    if (world == null) {
      throw new IllegalArgumentException("Read only world object cannot be null");
    }
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    playerType = new JPanel();
    playerTypeLabel = new JLabel("Player Type");
    playerTypeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    playerType.add(playerTypeLabel);
    playerTypeComboBox = new JComboBox<String>(playerTypeComboBoxItems);
    playerType.add(playerTypeComboBox);
    humanPlayer = new JPanel();
    humanPlayerLabels = new JPanel();
    humanPlayerLabels.setLayout(new BoxLayout(humanPlayerLabels, BoxLayout.Y_AXIS));
    playerNameLabel = new JLabel("Player Name");
    playerNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    playerLocationLabel = new JLabel("Player Location");
    playerLocationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    humanPlayerLabels.add(playerNameLabel);
    humanPlayerLabels.add(playerLocationLabel);
    humanPlayer.add(humanPlayerLabels);
    playerNameTextArea = new JTextArea();
    playerLocationComboBox = new JComboBox<String>(world.getSpaces());
    humanPlayerEntries = new JPanel();
    humanPlayerEntries.setLayout(new BoxLayout(humanPlayerEntries, BoxLayout.Y_AXIS));
    humanPlayerEntries.add(playerNameTextArea);
    humanPlayerEntries.add(playerLocationComboBox);
    humanPlayer.add(humanPlayerEntries);
    buttonsPanel = new JPanel();
    addPlayer = new JButton("Add Player");
    startGame = new JButton("Start Game");
    buttonsPanel.add(addPlayer);
    buttonsPanel.add(startGame);
    this.add(playerType);
    this.add(humanPlayer);
    this.add(buttonsPanel);
    playerType.setMaximumSize(playerType.getPreferredSize());
    humanPlayer.setMaximumSize(humanPlayer.getPreferredSize());
    buttonsPanel.setMaximumSize(buttonsPanel.getPreferredSize());

  }

  public void setFeatures(Features f) {
    playerTypeComboBox.addItemListener(l -> this.switchComputerToHumanScreen());
    addPlayer
        .addActionListener(l -> f.addPlayer(String.valueOf(playerTypeComboBox.getSelectedItem()),
            playerNameTextArea.getText(),
            String.valueOf(playerLocationComboBox.getSelectedItem())));
    startGame.addActionListener(l -> f.switchToGameScreen());
  }

  private void switchComputerToHumanScreen() {
    String playerType = String.valueOf(playerTypeComboBox.getSelectedItem());
    if (playerType.equals(humanPlayerPanel)) {
      humanPlayer.setVisible(true);
    }
    if (playerType.equals(computerPlayerPanel)) {
      humanPlayer.setVisible(false);
      playerNameTextArea.setText("");
      playerLocationComboBox.setSelectedIndex(-1);
    }
  }
}
