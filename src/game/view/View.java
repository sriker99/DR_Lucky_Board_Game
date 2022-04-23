package game.view;

import game.controller.Features;

public interface View {

	void setFeatures(Features f);

	void showItemsDialog(String title, String[] items);

	void showSuccessMessage(String title, String message);

	void showErrorMessage(String message);
	void resetFocus();
	void changeToWelcomeScreen();
	void changeToPlayerConfigScreen();
	void changeToGameScreen();
	void disposeFrame();

}
