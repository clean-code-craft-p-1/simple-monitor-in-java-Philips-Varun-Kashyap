package vitals;

final class VitalAlerter {
  private static final int ALERT_REPEAT_COUNT = 6;

  private final long alertSleepMillis;
  private final Sleeper sleeper;

  VitalAlerter(long alertSleepMillis, Sleeper sleeper) {
    this.alertSleepMillis = alertSleepMillis;
    this.sleeper = sleeper;
  }

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