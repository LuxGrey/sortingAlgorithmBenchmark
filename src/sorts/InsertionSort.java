package sorts;

import orderable.Orderable;

// based on Ottman, Widmayer, 2017, page 159 - 160
public class InsertionSort extends SortAlgorithm {

  public static void sort(Orderable A[]) {
    for (int i = 2; i < A.length; i++) {
      Orderable temp = A[i];
      int j = i - 1;
      while (j >= 1 && A[j].greater(temp)) {
        A[j + 1] = A[j];
        j--;
      }
      A[j + 1] = temp;
    }
  }
}
