package game.controller;

public interface Features {

  void exitProgram();

  void addPlayer(String playerType, String playerName, String playerLocation);

  void startGame();

  void switchToWelcomeScreen();

  void switchToPlayerConfigScreen();

  void switchToGameScreen();

  void playGameWithUploadedFile(String filePath);
}
