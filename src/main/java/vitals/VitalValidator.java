package vitals;

/**
 * Validates measured vital values against the configured ranges.
 */
final class VitalValidator {
  private final VitalLimits vitalLimits;

  /**
   * Creates a validator that uses the supplied configured limits.
   *
   * @param vitalLimits the configured limits for each vital sign
   */
  VitalValidator(VitalLimits vitalLimits) {
    this.vitalLimits = vitalLimits;
  }

  /**
   * Validates the supplied vital values in the configured evaluation order.
   *
   * @param temperature the measured body temperature in Fahrenheit
   * @param pulseRate the measured pulse rate in beats per minute
   * @param spo2 the measured oxygen saturation percentage
   * @return the first validation failure message, or {@code null} when all values are valid
   */
  String validate(float temperature, float pulseRate, float spo2) {
    String temperatureValidation = validateVital(temperature, vitalLimits.getTemperatureRange());
    if (temperatureValidation != null) {
      return temperatureValidation;
    }

    String pulseRateValidation = validateVital(pulseRate, vitalLimits.getPulseRateRange());
    if (pulseRateValidation != null) {
      return pulseRateValidation;
    }

    return validateVital(spo2, vitalLimits.getSpo2Range());
  }

  /**
   * Checks a single vital value against a configured range.
   *
   * @param value the measured value to validate
   * @param vitalRange the configured acceptable range and failure message
   * @return the out-of-range message, or {@code null} when the value is valid
   */
  private String validateVital(float value, VitalRange vitalRange) {
    if (!vitalRange.contains(value)) {
      return vitalRange.getOutOfRangeMessage();
    }
    return null;
  }
}