package vitals;

/**
 * Abstraction for pause behavior used by alert rendering.
 */
interface Sleeper {
  /** Pauses execution for the supplied duration. */
  void sleep(long millis) throws InterruptedException;
}