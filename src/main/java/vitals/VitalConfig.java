package vitals;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Loads vital limits and alert settings from the application YAML configuration.
 */
final class VitalConfig {
  private static final String CONFIG_FILE = "application.yml";

  /**
   * Prevents instantiation of this utility class.
   */
  private VitalConfig() {
  }

  /**
   * Loads and parses the configured ranges and alert timing.
   *
   * @return the configured vital limits and alert settings
   * @throws IllegalStateException when the configuration file is missing or invalid
   */
  static VitalLimits loadLimits() {
    try (InputStream inputStream = VitalConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
      if (inputStream == null) {
        throw new IllegalStateException("Missing configuration file: " + CONFIG_FILE);
      }

      Yaml yaml = new Yaml();
      Map<String, Object> root = yaml.load(inputStream);
      if (root == null) {
        throw new IllegalStateException("Configuration file is empty: " + CONFIG_FILE);
      }

      Map<String, Object> vitals = getMap(root, "vitals");
      Map<String, Object> alert = getMap(root, "alert");
      return new VitalLimits(
          getRange(vitals, "temperature", "Temperature is critical!"),
          getRange(vitals, "pulseRate", "Pulse Rate is out of range!"),
          getRange(vitals, "spo2", "Oxygen Saturation out of range!"),
          getLong(alert, "sleepMillis"));
    } catch (Exception exception) {
      throw new IllegalStateException("Unable to load vital limits from configuration", exception);
    }
  }

  @SuppressWarnings("unchecked")
  /**
   * Reads a nested map from the parsed YAML structure.
   *
   * @param source the source map to read from
   * @param key the nested map key
   * @return the nested map for the supplied key
   */
  private static Map<String, Object> getMap(Map<String, Object> source, String key) {
    Object value = source.get(key);
    if (!(value instanceof Map)) {
      throw new IllegalStateException("Invalid configuration for key: " + key);
    }
    return (Map<String, Object>) value;
  }

  /**
   * Builds a vital range from YAML min and max values.
   *
   * @param vitals the parsed vitals section
   * @param key the vital key to read
   * @param message the message to return when the value is out of range
   * @return the configured vital range
   */
  private static VitalRange getRange(Map<String, Object> vitals, String key, String message) {
    Map<String, Object> range = getMap(vitals, key);
    float min = getNumber(range, "min");
    float max = getNumber(range, "max");
    return new VitalRange(min, max, message);
  }

  /**
   * Reads a floating-point numeric value from configuration.
   *
   * @param source the source map to read from
   * @param key the numeric key to read
   * @return the configured number as a float
   */
  private static float getNumber(Map<String, Object> source, String key) {
    Object value = source.get(key);
    if (!(value instanceof Number)) {
      throw new IllegalStateException("Invalid numeric configuration for key: " + key);
    }
    return ((Number) value).floatValue();
  }

  /**
   * Reads an integer-like numeric value from configuration.
   *
   * @param source the source map to read from
   * @param key the numeric key to read
   * @return the configured number as a long
   */
  private static long getLong(Map<String, Object> source, String key) {
    Object value = source.get(key);
    if (!(value instanceof Number)) {
      throw new IllegalStateException("Invalid numeric configuration for key: " + key);
    }
    return ((Number) value).longValue();
  }
}