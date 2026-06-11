package vitals;

final class VitalValidator {
  private final VitalLimits vitalLimits;

  VitalValidator(VitalLimits vitalLimits) {
    this.vitalLimits = vitalLimits;
  }

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

  private String validateVital(float value, VitalRange vitalRange) {
    if (!vitalRange.contains(value)) {
      return vitalRange.getOutOfRangeMessage();
    }
    return null;
  }
}