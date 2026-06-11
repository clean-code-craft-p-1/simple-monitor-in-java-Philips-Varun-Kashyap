package vitals;

/**
 * Abstraction for pause behavior used by alert rendering.
 */
interface Sleeper {
  /**
   * Pauses execution for the supplied duration.
   *
   * @param millis the duration to sleep in milliseconds
   * @throws InterruptedException when the sleep is interrupted
   */
  void sleep(long millis) throws InterruptedException;
}