package game.view;

import game.controller.Features;

public interface View {
	 void setFeatures(Features f);
	 void showSuccessMessage(String message);
	 void showErrorMessage(String message);
	void showItemsDialog();
	void resetFocus();
	void showPickItemsDialog();

	void showMessageDialog(String title,String response);
	void changeToWelcomeScreen();

	void changeToPlayerConfigScreen();

	void changeToGameScreen();

	void disposeFrame();
}
