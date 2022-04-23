package game.controller;

import game.controller.commands.Attack;
import game.controller.commands.LookAround;
import game.controller.commands.PickItem;

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
    view.resetFocus();
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
        wc.playGame(model,view);
        playerCount += 1;
        view.showSuccessMessage("Computer player is added to the world");
      } catch (IllegalArgumentException ie) {
        view.showErrorMessage(ie.getMessage());
      }
    } else if (playerType.equals("HUMAN")) {
      try {
        WorldController wc = new AddPlayer(playerName, playerLocation);
        wc.playGame(model,view);
        playerCount += 1;
        String result = String.format("%s player is added to the world", playerName);
        view.showSuccessMessage(result);
      } catch (IllegalArgumentException ie) {
        view.showErrorMessage(ie.getMessage());
      }
    }
  }

  @Override
  public void attack(String item) {
    WorldController wc= new Attack(item);
    wc.playGame(model,view);
  }

  @Override
  public void displayAttackItemDialog(){
    view.showItemsDialog();
  }

  @Override
  public void pick(String item) {
    WorldController wc= new PickItem(item);
    wc.playGame(model,view);
  }

  @Override
  public void displayPickItemDialog(){
    view.showPickItemsDialog();
  }
  @Override
  public void displayLookAround() {
    WorldController wc= new LookAround();
    wc.playGame(model,view);
  }
}