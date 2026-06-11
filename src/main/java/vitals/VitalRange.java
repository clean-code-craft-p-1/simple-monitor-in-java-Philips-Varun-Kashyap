package vitals;

/**
 * Represents an allowed numeric range for a vital sign and its failure message.
 */
final class VitalRange {
  private final float min;
  private final float max;
  private final String outOfRangeMessage;

  /**
   * Creates a configured range for a vital sign.
   *
   * @param min the inclusive minimum accepted value
   * @param max the inclusive maximum accepted value
   * @param outOfRangeMessage the message returned when the value is out of range
   */
  VitalRange(float min, float max, String outOfRangeMessage) {
    this.min = min;
    this.max = max;
    this.outOfRangeMessage = outOfRangeMessage;
  }

  /**
   * Checks whether a measured value falls within the configured bounds.
   *
   * @param value the measured value to evaluate
   * @return {@code true} when the value is within the configured bounds
   */
  boolean contains(float value) {
    return value >= min && value <= max;
  }

  /**
   * Returns the message associated with an out-of-range value.
   *
   * @return the out-of-range message
   */
  String getOutOfRangeMessage() {
    return outOfRangeMessage;
  }
}