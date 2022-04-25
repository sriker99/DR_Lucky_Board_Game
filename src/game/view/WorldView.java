package game.view;

import game.controller.Features;
import game.model.ReadOnlyWorld;
import game.view.panels.GameScreen;
import game.view.panels.PlayerPanel;
import game.view.panels.WelcomeScreen;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class represents the game with all the implementations of the
 * functionalities in the view and represents a frame.
 */

public class WorldView extends JFrame implements View {

  private static final long serialVersionUID = 1L;
  private final String welcomeCard = "WELCOMECARD";
  private final String playerConfigurationCard = "PLAYERCONFIGURATIONCARD";
  private final String gameCard = "GAMECARD";
  private PlayerPanel addPlayers;
  private GameScreen gamePanel;
  private ReadOnlyWorld world;
  private WelcomeScreen ws;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem menuItem1;
  private JMenuItem menuItem2;
  private JMenuItem menuItem3;
  private JComboBox itemsCombo;
  private JPanel cards;

  /**
   * Initialises the view of the world with the given world description.
   *
   * @param caption of the screen.
   * @param world   object.
   */
  public WorldView(String caption, ReadOnlyWorld world) throws IOException {
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
    ws = new WelcomeScreen();
    this.add(ws);
    this.world = world;
    cards = new JPanel(new CardLayout());
    ws = new WelcomeScreen();
    addPlayers = new PlayerPanel(this.world);
    gamePanel = new GameScreen(this.world);
    cards.add(ws, welcomeCard);
    cards.add(addPlayers, playerConfigurationCard);
    cards.add(gamePanel, gameCard);
    this.add(cards);
    this.setSize(500, 500);
    this.setLocation(500, 500);
    setPreferredSize(new Dimension(500, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalArgumentException("Features object cannot be null");
    }
    ws.setFeatures(f);
    addPlayers.setFeatures(f);
    menuItem1.addActionListener(l -> f.startGame());
    menuItem2.addActionListener(l -> this.uploadFile(f));
    menuItem3.addActionListener(l -> f.exitProgram());
    this.addMouseListener(new MouseListener() {
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

  @Override
  public void showSuccessMessage(String title, String message) {
    if (message == null || "".equals(message.trim())) {
      throw new IllegalArgumentException("message cannot be empty");
    }
    JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);

  }

  @Override
  public void showErrorMessage(String message) {
    if (message == null || "".equals(message.trim())) {
      throw new IllegalArgumentException("message cannot be empty");
    }
    JOptionPane.showMessageDialog(new JFrame(), message, "Player Status",
        JOptionPane.ERROR_MESSAGE);
  }

  public void changeToWelcomeScreen() {
    CardLayout c = (CardLayout) (cards.getLayout());
    c.show(cards, this.welcomeCard);
    menuItem1.setEnabled(true);
    menuItem2.setEnabled(true);
  }

  @Override
  public void changeToPlayerConfigScreen() {
    CardLayout c = (CardLayout) (cards.getLayout());
    c.show(cards, this.playerConfigurationCard);
    menuItem1.setEnabled(false);
    menuItem2.setEnabled(false);
  }

  @Override
  public void changeToGameScreen() {
    CardLayout c = (CardLayout) (cards.getLayout());
    c.show(cards, this.gameCard);
    menuItem1.setEnabled(false);
    menuItem2.setEnabled(false);
    this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
    this.setLocation(0, 0);
  }

  /**
   * Upload a new world specification file.
   *
   * @param f is the features object.
   */
  private void uploadFile(Features f) {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(getParent());
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      f.playGameWithUploadedFile(file.getPath());
    }
  }

  @Override
  public void disposeFrame() {
    this.setVisible(false);
    this.dispose();
  }

  @Override
  public void updateClues() {
    gamePanel.updateClues();
  }
}
