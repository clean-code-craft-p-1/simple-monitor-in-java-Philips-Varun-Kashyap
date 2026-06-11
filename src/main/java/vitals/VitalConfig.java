package vitals;
import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

final class VitalConfig {
  private static final String CONFIG_FILE = "application.yml";

  private VitalConfig() {
  }

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
  private static Map<String, Object> getMap(Map<String, Object> source, String key) {
    Object value = source.get(key);
    if (!(value instanceof Map)) {
      throw new IllegalStateException("Invalid configuration for key: " + key);
    }
    return (Map<String, Object>) value;
  }
  private static VitalRange getRange(Map<String, Object> vitals, String key, String message) {
    Map<String, Object> range = getMap(vitals, key);
    float min = getRequiredNumber(range, "min").floatValue();
    float max = getRequiredNumber(range, "max").floatValue();
    return new VitalRange(min, max, message);
  }

  private static Number getRequiredNumber(Map<String, Object> source, String key) {
    Object value = source.get(key);
    if (!(value instanceof Number)) {
      throw new IllegalStateException("Invalid numeric configuration for key: " + key);
    }
    return (Number) value;
  }
}