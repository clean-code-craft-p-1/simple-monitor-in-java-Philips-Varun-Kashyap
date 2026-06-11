package vitals;
import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

final class VitalConfig {
  private static final String CONFIG_FILE = "application.yml";
  private static final String VITALS_KEY = "vitals";
  private static final String ALERT_KEY = "alert";

  private VitalConfig() {
  }

  static VitalLimits loadLimits() {
    try (InputStream inputStream = getRequiredConfigStream()) {
      Map<String, Object> root = loadRequiredRoot(inputStream);
      return buildVitalLimits(root);
    } catch (Exception exception) {
      throw new IllegalStateException("Unable to load vital limits from configuration", exception);
    }
  }

  private static InputStream getRequiredConfigStream() {
    InputStream inputStream = VitalConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
    if (inputStream == null) {
      throw new IllegalStateException("Missing configuration file: " + CONFIG_FILE);
    }
    return inputStream;
  }

  private static Map<String, Object> loadRequiredRoot(InputStream inputStream) {
    Yaml yaml = new Yaml();
    Map<String, Object> root = yaml.load(inputStream);
    if (root == null) {
      throw new IllegalStateException("Configuration file is empty: " + CONFIG_FILE);
    }
    return root;
  }

  private static VitalLimits buildVitalLimits(Map<String, Object> root) {
    Map<String, Object> vitals = getMap(root, VITALS_KEY);
    Map<String, Object> alert = getMap(root, ALERT_KEY);
    return new VitalLimits(
        getRange(vitals, "temperature", "Temperature is critical!"),
        getRange(vitals, "pulseRate", "Pulse Rate is out of range!"),
        getRange(vitals, "spo2", "Oxygen Saturation out of range!"),
        getRequiredNumber(alert, "sleepMillis").longValue());
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