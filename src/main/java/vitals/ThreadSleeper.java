package vitals;

/**
 * Production sleeper that delegates to {@link Thread#sleep(long)}.
 */
final class ThreadSleeper implements Sleeper {
  @Override
  /** Delegates the pause to {@link Thread#sleep(long)}. */
  public void sleep(long millis) throws InterruptedException { Thread.sleep(millis); }
}