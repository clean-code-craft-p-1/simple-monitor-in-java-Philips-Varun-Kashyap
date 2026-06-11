package vitals;


public abstract class VitalsChecker {
  private static final VitalLimits VITAL_LIMITS = VitalConfig.loadLimits();
  private static final VitalValidator VALIDATOR = new VitalValidator(VITAL_LIMITS);
  private static final VitalAlerter ALERTER =
      new VitalAlerter(VITAL_LIMITS.getAlertSleepMillis(), new ThreadSleeper());

  static boolean vitalsOk(float temperature, float pulseRate, float spo2) 
      throws InterruptedException {
    String validationMessage = VALIDATOR.validate(temperature, pulseRate, spo2);
    if (validationMessage != null) {
      ALERTER.printAlert(validationMessage);
      return false;
    }
    return true;
  }
}
