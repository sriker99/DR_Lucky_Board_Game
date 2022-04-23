package game.controller;


public interface Features {

void exitProgram();
void addPlayer(String playerType,String playerName,String playerLocation);

  void attack(String item);
  void displayAttackItemDialog();
  void displayLookAround();
  void pick(String item);
  void displayPickItemDialog();
  void startGame();

  void switchToWelcomeScreen();

  void switchToPlayerConfigScreen();

  void switchToGameScreen();

  void playGameWithUploadedFile(String filePath);

}
