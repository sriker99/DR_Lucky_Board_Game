package game.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.controller.Features;
import game.model.ReadOnlyWorld;
import game.view.panels.PlayerPanel;

public class WorldView extends JFrame implements View {

  private static final long serialVersionUID = 1L;
  PlayerPanel addPlayers;
  ReadOnlyWorld world;

  public WorldView(String caption, ReadOnlyWorld world) {
    if (caption == null || "".equals(caption.trim())) {
      throw new IllegalArgumentException("Caption shouldn't be empty");
    }
    if (world == null) {
      throw new IllegalArgumentException("World object cannot be null");
    }
    this.world = world;
    addPlayers = new PlayerPanel(this.world);
    this.add(addPlayers);
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
    addPlayers.setFeatures(f);
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

}
