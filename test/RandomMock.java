import game.model.RandomGenerator;

/**
 * A mock model for Random interface.
 */
public class RandomMock implements RandomGenerator {

  private final int[] arr;
  private final int uniqueId;
  private int index;
  private StringBuilder log;

  /**
   * Constructor to initialize fields.
   *
   * @param args varargs
   */
  public RandomMock(StringBuilder log, int uid, int... args) {
    this.arr = new int[args.length];
    for (int i = 0; i < args.length; i++) {
      this.arr[i] = args[i];
    }
    this.index = 0;
    this.log = log;
    this.uniqueId = uid;
  }

  @Override
  public int getRandomNumber() {
    log.append(String.format("random is invoked %d", this.uniqueId));
    int n = arr[this.index];
    this.index++;
    return n;
  }
}
