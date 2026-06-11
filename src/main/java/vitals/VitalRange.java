package vitals;

final class VitalRange {
  private final float min;
  private final float max;
  private final String outOfRangeMessage;

  VitalRange(float min, float max, String outOfRangeMessage) {
    this.min = min;
    this.max = max;
    this.outOfRangeMessage = outOfRangeMessage;
  }

  boolean contains(float value) {
    return value >= min && value <= max;
  }

  String getOutOfRangeMessage() {
    return outOfRangeMessage;
  }
}