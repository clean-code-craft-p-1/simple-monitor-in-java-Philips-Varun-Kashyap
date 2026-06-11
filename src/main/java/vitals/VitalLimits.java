package vitals;

final class VitalLimits {
  private final VitalRange temperatureRange;
  private final VitalRange pulseRateRange;
  private final VitalRange spo2Range;
  private final long alertSleepMillis;

  VitalLimits(VitalRange temperatureRange, VitalRange pulseRateRange, VitalRange spo2Range,
      long alertSleepMillis) {
    this.temperatureRange = temperatureRange;
    this.pulseRateRange = pulseRateRange;
    this.spo2Range = spo2Range;
    this.alertSleepMillis = alertSleepMillis;
  }

  VitalRange getTemperatureRange() {
    return temperatureRange;
  }

  VitalRange getPulseRateRange() {
    return pulseRateRange;
  }

  VitalRange getSpo2Range() {
    return spo2Range;
  }

  long getAlertSleepMillis() {
    return alertSleepMillis;
  }
}