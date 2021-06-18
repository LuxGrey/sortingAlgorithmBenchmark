package sorts;

import orderable.Orderable;

public class BubbleSort extends SortAlgorithm {

  public static void sort(Orderable A[]) {
    int i;
    boolean swapHappened;
    Orderable temp;
    do {
      swapHappened = false;
      for (i = 1; i < A.length - 1; i++) {
        if (A[i].greater(A[i + 1])) {
          swap(A, i, i + 1);
          swapHappened = true;
        }
      }
    } while (swapHappened);
  }
}
