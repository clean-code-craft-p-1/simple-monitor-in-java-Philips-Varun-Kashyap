package vitals;


/**
 * Entry point for evaluating a patient's vital signs against configured limits.
 */
public abstract class VitalsChecker {
  private static final VitalLimits VITAL_LIMITS = VitalConfig.loadLimits();
  private static final VitalValidator VALIDATOR = new VitalValidator(VITAL_LIMITS);
  private static final VitalAlerter ALERTER =
      new VitalAlerter(VITAL_LIMITS.getAlertSleepMillis(), new ThreadSleeper());

  /**
   * Checks temperature, pulse rate, and oxygen saturation against configured ranges.
   *
   * @param temperature the measured body temperature in Fahrenheit
   * @param pulseRate the measured pulse rate in beats per minute
   * @param spo2 the measured oxygen saturation percentage
   * @return {@code true} when all vitals are within range, otherwise {@code false}
   * @throws InterruptedException when the alert animation sleep is interrupted
   */
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
