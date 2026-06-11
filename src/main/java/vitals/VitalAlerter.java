package vitals;

/**
 * Prints a visual alert when a vital sign falls outside the configured range.
 */
final class VitalAlerter {
  private static final int ALERT_REPEAT_COUNT = 6;

  private final long alertSleepMillis;
  private final Sleeper sleeper;

  /**
   * Creates an alerter with the supplied delay strategy.
   *
   * @param alertSleepMillis the delay between alert frames in milliseconds
   * @param sleeper the sleep strategy used between alert frames
   */
  VitalAlerter(long alertSleepMillis, Sleeper sleeper) {
    this.alertSleepMillis = alertSleepMillis;
    this.sleeper = sleeper;
  }

  /**
   * Prints the supplied validation message and flashes the console alert pattern.
   *
   * @param validationMessage the message describing the vital failure
   * @throws InterruptedException when the alert sleep is interrupted
   */
  void printAlert(String validationMessage) throws InterruptedException {
    System.out.println(validationMessage);
    for (int i = 0; i < ALERT_REPEAT_COUNT; i++) {
      System.out.print("\r* ");
      sleeper.sleep(alertSleepMillis);
      System.out.print("\r *");
      sleeper.sleep(alertSleepMillis);
    }
  }
}