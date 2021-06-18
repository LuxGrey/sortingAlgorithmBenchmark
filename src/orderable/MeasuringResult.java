package orderable;

public class MeasuringResult {
  private long measuredTime; // in Millisekunden
  private int measurements;
  private int sortedDataAmount;
  private int keyRange;

  public MeasuringResult(int sortedDataAmount, int keyRange) {
    measuredTime = 0L;
    measuredTime = 0;
    this.sortedDataAmount = sortedDataAmount;
    this.keyRange = keyRange;
  }

  public void addMeasuredTime(long time) {
    measuredTime = ((measuredTime * measurements) + (time)) / (measurements + 1);
    measurements++;
  }

  public long getMeasuredTime() {
    return measuredTime;
  }

  public int getSortedDataAmount() {
    return sortedDataAmount;
  }

  public int getKeyRange() {
    return keyRange;
  }
}
