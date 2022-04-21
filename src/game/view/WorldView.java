package game.view;

import game.view.panels.WelcomeScreen;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.controller.Features;
import game.model.ReadOnlyWorld;
import game.view.panels.GameScreen;
import game.view.panels.PlayerPanel;

public class WorldView extends JFrame implements View {

  private static final long serialVersionUID = 1L;
  private PlayerPanel addPlayers;
  private ReadOnlyWorld world;
  private WelcomeScreen ws;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem menuItem1;
  private JMenuItem menuItem2;
  private JMenuItem menuItem3;
  private JPanel cards;
  private GameScreen gameScreen;
  private final String welcomeCard = "WELCOMECARD";
  private final String playerConfigurationCard = "PLAYERCONFIGURATIONCARD";
  private final String gameCard = "GAMECARD";

  public WorldView(String caption, ReadOnlyWorld world) {
    if (caption == null || "".equals(caption.trim())) {
      throw new IllegalArgumentException("Caption shouldn't be empty");
    }
    if (world == null) {
      throw new IllegalArgumentException("World object cannot be null");
    }
    menuBar = new JMenuBar();
    menu = new JMenu("Menu");
    menuBar.add(menu);
    menuItem1 = new JMenuItem("New Game");
    menu.add(menuItem1);
    menuItem2 = new JMenuItem("Upload file");
    menu.add(menuItem2);
    menuItem3 = new JMenuItem("Quit Game");
    menu.add(menuItem3);
    this.setJMenuBar(menuBar);
    this.world = world;
    cards = new JPanel(new CardLayout());
    ws = new WelcomeScreen();
    addPlayers = new PlayerPanel(world);
    gameScreen = new GameScreen();
    cards.add(ws, welcomeCard);
    cards.add(addPlayers, playerConfigurationCard);
    cards.add(gameScreen, gameCard);
    this.add(cards);
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  @Override
  public void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalArgumentException("Features object cannot be null");
    }
    ws.setFeatures(f);
    addPlayers.setFeatures(f);
    menuItem1.addActionListener(l -> f.startGame());
  }

  @Override
  public void showSuccessMessage(String message) {
    if (message == null || "".equals(message.trim())) {
      throw new IllegalArgumentException("message cannot be empty");
    }
    JOptionPane.showMessageDialog(new JFrame(), message, "Player Status",
        JOptionPane.INFORMATION_MESSAGE);

  }

  @Override
  public void showErrorMessage(String message) {
    if (message == null || "".equals(message.trim())) {
      throw new IllegalArgumentException("message cannot be empty");
    }
    JOptionPane.showMessageDialog(new JFrame(), message, "Player Status",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void changeToWelcomeScreen() {
    CardLayout c = (CardLayout) (cards.getLayout());
    c.show(cards, this.welcomeCard);
  }

  @Override
  public void changeToPlayerConfigScreen() {
    CardLayout c = (CardLayout) (cards.getLayout());
    c.show(cards, this.playerConfigurationCard);

  }

  @Override
  public void changeToGameScreen() {
    CardLayout c = (CardLayout) (cards.getLayout());
    c.show(cards, this.gameCard);

  }

}
