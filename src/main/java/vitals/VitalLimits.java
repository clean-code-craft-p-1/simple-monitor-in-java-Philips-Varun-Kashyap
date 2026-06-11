package vitals;

/**
 * Holds configured limits for each vital sign and alert timing settings.
 */
final class VitalLimits {
  final VitalRange temperatureRange;
  final VitalRange pulseRateRange;
  final VitalRange spo2Range;
  final long alertSleepMillis;

  /** Creates an immutable set of configured limits and alert settings. */
  VitalLimits(VitalRange temperatureRange, VitalRange pulseRateRange, VitalRange spo2Range,
      long alertSleepMillis) {
    this.temperatureRange = temperatureRange;
    this.pulseRateRange = pulseRateRange;
    this.spo2Range = spo2Range;
    this.alertSleepMillis = alertSleepMillis;
  }
}