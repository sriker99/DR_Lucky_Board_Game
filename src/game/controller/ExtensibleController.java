package game.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import game.controller.commands.AddComputerPlayer;
import game.controller.commands.AddPlayer;
import game.model.ConcreteWorld;
import game.model.RandomGen;
import game.model.World;
import game.view.View;
import game.view.WorldView;

public class ExtensibleController implements Controller, Features {
  private World model;
  private View view;
  private int playerCount;
  private int maxNoOfTurns;
  private boolean gameStatus;
  private String filePath;

  public ExtensibleController(World model, int numOfTurns, String filePath) {
    if (model == null) {
      throw new IllegalArgumentException("World Object cannot be null");
    }
    if (numOfTurns < 0) {
      throw new IllegalArgumentException("Number of turns cannot be less than zero");
    }
    if (filePath == null || "".equals(filePath.trim())) {
      throw new IllegalArgumentException("File path cannot be empty");
    }
    this.model = model;
    playerCount = 0;
    this.maxNoOfTurns = numOfTurns;
    this.gameStatus = false;
    this.filePath = filePath;
  }

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
        if (playerCount == 10) {
          view.changeToGameScreen();
        }
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
        if (playerCount == 10) {
          view.changeToGameScreen();
        }
        view.showSuccessMessage(result);
      } catch (IllegalArgumentException ie) {
        view.showErrorMessage(ie.getMessage());
      }
    }
  }

  @Override
  public void startGame() {
    Readable file;
    try {
      file = new FileReader(this.filePath);
      World model = new ConcreteWorld(file, new RandomGen(), maxNoOfTurns);
      this.model = model;
      View view = new WorldView("Doctor Lucky", model);
      this.gameStatus = false;
      this.execute(view);
    } catch (FileNotFoundException e) {
      view.showErrorMessage(e.getMessage());
    }
  }

  @Override
  public void switchToWelcomeScreen() {
    view.changeToWelcomeScreen();

  }

  @Override
  public void switchToPlayerConfigScreen() {
    view.changeToPlayerConfigScreen();

  }

  @Override
  public void switchToGameScreen() {
    view.changeToGameScreen();

  }

}