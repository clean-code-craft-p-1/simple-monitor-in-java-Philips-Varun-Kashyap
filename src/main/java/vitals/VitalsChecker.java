package vitals;


public abstract class VitalsChecker {
  private static final VitalLimits VITAL_LIMITS = VitalConfig.loadLimits();
  private static final VitalValidator VALIDATOR = new VitalValidator(VITAL_LIMITS);

  static boolean vitalsOk(float temperature, float pulseRate, float spo2) 
      throws InterruptedException {
    String validationMessage = VALIDATOR.validate(temperature, pulseRate, spo2);
    if (validationMessage != null) {
      printAlert(validationMessage);
      return false;
    }
    return true;
  }

  private static void printAlert(String validationMessage) throws InterruptedException {
    System.out.println(validationMessage);
    for (int i = 0; i < 6; i++) {
      System.out.print("\r* ");
      Thread.sleep(VITAL_LIMITS.getAlertSleepMillis());
      System.out.print("\r *");
      Thread.sleep(VITAL_LIMITS.getAlertSleepMillis());
    }
  }
}
