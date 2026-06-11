package vitals;

/**
 * Production sleeper that delegates to {@link Thread#sleep(long)}.
 */
final class ThreadSleeper implements Sleeper {
  /**
   * Suspends the current thread for the supplied duration.
   *
   * @param millis the duration to sleep in milliseconds
   * @throws InterruptedException when the sleep is interrupted
   */
  @Override
  public void sleep(long millis) throws InterruptedException {
    Thread.sleep(millis);
  }
}