package game.view;

import game.controller.Features;
import java.io.IOException;

/**
 * This interfaces contains method signatures of functionalities where certain operations are done.
 */
public interface View {

  /**
   * Performing operations when certain events are done.
   *
   * @param f is a features object.
   */
  void setFeatures(Features f);

  /**
   * Pops up a dialog box where user can select an option.
   *
   * @param title is the name of the dialog box
   * @param items options in the dialog box.
   */
  void showItemsDialog(String title, String[] items);

  /**
   * Pops up a dialog box with the given message.
   *
   * @param title   is the name of the dialog box.
   * @param message to be displayed in the dialog box.
   */
  void showSuccessMessage(String title, String message);

  /**
   * Pops up a dialog box with the given error message.
   *
   * @param message to be displayed in the dialog box.
   */
  void showErrorMessage(String message);

  /**
   * Changing focus to the given screen.
   */
  void resetFocus();

  /**
   * Navigating to welcome screen.
   */
  void changeToWelcomeScreen();

  /**
   * Navigating to player adding screen.
   */
  void changeToPlayerConfigScreen();

  /**
   * Navigating to game screen.
   */
  void changeToGameScreen();

  /**
   * Kill the frame.
   */
  void disposeFrame();

  /**
   * Updates clues after every turn.
   */
  void updateClues();
}
