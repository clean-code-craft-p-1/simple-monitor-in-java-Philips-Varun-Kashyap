package vitals;

/**
 * Holds configured limits for each vital sign and alert timing settings.
 */
final class VitalLimits {
  private final VitalRange temperatureRange;
  private final VitalRange pulseRateRange;
  private final VitalRange spo2Range;
  private final long alertSleepMillis;

  /**
   * Creates an immutable set of configured limits and alert settings.
   *
   * @param temperatureRange the allowed temperature range
   * @param pulseRateRange the allowed pulse rate range
   * @param spo2Range the allowed oxygen saturation range
   * @param alertSleepMillis the alert animation delay in milliseconds
   */
  VitalLimits(VitalRange temperatureRange, VitalRange pulseRateRange, VitalRange spo2Range,
      long alertSleepMillis) {
    this.temperatureRange = temperatureRange;
    this.pulseRateRange = pulseRateRange;
    this.spo2Range = spo2Range;
    this.alertSleepMillis = alertSleepMillis;
  }

  /**
   * Returns the configured temperature range.
   *
   * @return the configured temperature range
   */
  VitalRange getTemperatureRange() {
    return temperatureRange;
  }

  /**
   * Returns the configured pulse rate range.
   *
   * @return the configured pulse rate range
   */
  VitalRange getPulseRateRange() {
    return pulseRateRange;
  }

  /**
   * Returns the configured oxygen saturation range.
   *
   * @return the configured oxygen saturation range
   */
  VitalRange getSpo2Range() {
    return spo2Range;
  }

  /**
   * Returns the configured alert delay.
   *
   * @return the alert sleep time in milliseconds
   */
  long getAlertSleepMillis() {
    return alertSleepMillis;
  }
}