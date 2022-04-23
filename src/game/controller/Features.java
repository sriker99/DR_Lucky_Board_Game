package game.controller;


public interface Features {

void exitProgram();
void addPlayer(String playerType,String playerName,String playerLocation);

  void attack(String item);

  void displayItemsDialog(String title, String[] items);

  void displayErrorDialog(String msg);

  void displayLookAround();
  void pick(String item);
  void startGame();

  void switchToWelcomeScreen();

  void switchToPlayerConfigScreen();

  void switchToGameScreen();

  void playGameWithUploadedFile(String filePath);

  void movePet(String location);
}
