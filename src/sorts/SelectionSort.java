package sorts;

import orderable.Orderable;

// based on Ottman, Widmayer, 2017, pages 83 - 84
public class SelectionSort extends SortAlgorithm {

  public static void sort(Orderable A[]) {
    int i, j, min;
    for (i = 1; i < A.length - 1; i++) {
      min = i;
      for (j = i + 1; j < A.length; j++) {
        if (A[j].less(A[min])) {
          min = j;
        }
      }
      swap(A, i, min);
    }
  }
}
