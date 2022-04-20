package game.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import game.controller.commands.AddComputerPlayer;
import game.controller.commands.AddPlayer;
import game.model.World;
import game.view.View;

public class ExtensibleController implements Controller, Features {
  private World model;
  private View view;
  private int playerCount;

  public ExtensibleController(World model) {
    if (model == null) {
      throw new IllegalArgumentException("World Object cannot be null");
    }
    this.model = model;
    playerCount = 0;
  }

  @Override
  public void execute(View view) {
    if (view == null) {
      throw new IllegalArgumentException("View Object cannot be null");
    }
    this.view = view;

    view.setFeatures(this);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void addPlayer(String playerType, String playerName, String playerLocation) {

    if (playerType == null || "".equals(playerType.trim())) {
      view.showErrorMessage("Player type cannot be null");
    }
    if (playerName == null) {
      view.showErrorMessage("Player name cannot be null");
    }
    if (playerLocation == null) {
      view.showErrorMessage("Player location cannot be null");
    }
    if (playerType.equals("COMPUTER")) {
      try {
        WorldController wc = new AddComputerPlayer();
        wc.playGame(model);
        playerCount += 1;
        view.showSuccessMessage("Computer player is added to the world");
      } catch (IllegalArgumentException ie) {
        view.showErrorMessage(ie.getMessage());
      }
    } else if (playerType.equals("HUMAN")) {
      try {
        WorldController wc = new AddPlayer(playerName, playerLocation);
        wc.playGame(model);
        playerCount += 1;
        String result = String.format("%s player is added to the world", playerName);
        view.showSuccessMessage(result);
      } catch (IllegalArgumentException ie) {
        view.showErrorMessage(ie.getMessage());
      }
    }
  }

}