package game.view;

import game.view.panels.GameScreen;
import game.view.panels.WelcomeScreen;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import game.controller.Features;
import game.model.ReadOnlyWorld;
import game.view.panels.PlayerPanel;

public class WorldView extends JFrame implements View {

  private static final long serialVersionUID = 1L;
  private PlayerPanel addPlayers;
  private ReadOnlyWorld world;
  private GameScreen gamePanel;
  private WelcomeScreen ws;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem menuItem1;
  private JMenuItem menuItem2;
  private JMenuItem menuItem3;
  private JComboBox itemOptions;
  private JComboBox spaceItemOptions;

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
    menuItem1=new JMenuItem("New Game");
    menu.add(menuItem1);
    menuItem3=new JMenuItem("Upload file");
    menu.add(menuItem3);
    menuItem2=new JMenuItem("Quit Game");
    menu.add(menuItem2);
    itemOptions=new JComboBox<>(world.getPlayerItems());
    spaceItemOptions=new JComboBox<>(world.getSpaceItems());
    this.setJMenuBar(menuBar);
    ws=new WelcomeScreen();
    this.add(ws);
    this.world = world;
    addPlayers = new PlayerPanel(this.world);
    this.add(addPlayers);
    gamePanel=new GameScreen(this.world);
    this.add(gamePanel);
    setSize(500, 500);
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
    addPlayers.setFeatures(f);
    gamePanel.setFeatures(f);

    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        System.out.println("ravi");
        if (e.getKeyChar() == 'a') {
          f.displayAttackItemDialog();
        }
        if (e.getKeyChar() == '1') {
          f.displayLookAround();
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
  public void showItemsDialog(){
    JOptionPane.showMessageDialog(new JFrame(), itemOptions, "Attack with item",
        JOptionPane.QUESTION_MESSAGE);
  }

  @Override
  public void showPickItemsDialog(){
    JOptionPane.showMessageDialog(new JFrame(), spaceItemOptions, "Attack with item",
        JOptionPane.QUESTION_MESSAGE);
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
  public void showMessageDialog(String title,String message){
    JOptionPane.showMessageDialog(new JFrame(), message,title ,
        JOptionPane.INFORMATION_MESSAGE);
  }
}
