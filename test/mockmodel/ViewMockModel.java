package mockmodel;

import game.controller.Features;
import game.view.View;

/**
 * This mock class implements view interface to mock the actions performed by
 * world view class.
 */
public class ViewMockModel implements View {
  private final int uniqueCode;
  private StringBuilder log;

  /**
   * This is a constructor to initialize the view mock object with given values.
   *
   * @param log        is an input appender.
   * @param uniqueCode is a unique value to differentiate between methods.
   */
  public ViewMockModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void setFeatures(Features f) {
    log.append("set features method is invoked\n");
    log.append(String.format("unique code %;d\n", uniqueCode));

  }

  @Override
  public void showItemsDialog(String title, String[] items) {
    log.append("showItemsDialog method is invoked\n");
    log.append(String.format("Inputs title and items: %s %s\n", title, items.toString()));
    log.append(String.format("unique code %;d\n", uniqueCode));

  }

  @Override
  public void showSuccessMessage(String title, String message) {
    log.append("showSuccessMessage method is invoked\n");
    log.append(String.format("Inputs title and message: %s %s\n", title, message));
    log.append(String.format("unique code %;d\n", uniqueCode));

  }

  @Override
  public void showErrorMessage(String message) {
    log.append("showSuccessMessage method is invoked\n");
    log.append(String.format("Inputs message:%s\n", message));
    log.append(String.format("unique code %;d\n", uniqueCode));
  }

  @Override
  public void resetFocus() {
    log.append("resetFocus method is invoked\n");
    log.append(String.format("unique code %;d\n", uniqueCode));

  }

  @Override
  public void changeToWelcomeScreen() {

    log.append("changeToWelcomeScreen method is invoked\n");
    log.append(String.format("unique code %;d\n", uniqueCode));
  }

  @Override
  public void changeToPlayerConfigScreen() {
    log.append("changeToPlayerConfigScreen method is invoked\n");
    log.append(String.format("unique code %;d\n", uniqueCode));

  }

  @Override
  public void changeToGameScreen() {
    log.append("changeToGameScreen method is invoked\n");
    log.append(String.format("unique code %;d\n", uniqueCode));

  }

  @Override
  public void disposeFrame() {
    log.append("disposeFrame method is invoked\n");
    log.append(String.format("unique code %;d\n", uniqueCode));

  }

  @Override
  public void updateClues() {
    log.append("updateClues method is invoked\n");
    log.append(String.format("unique code %;d\n", uniqueCode));

  }

}
