package sorts;

import orderable.Orderable;

public class HeapSort extends SortAlgorithm {

  public static void sort(Orderable A[]) {
    int i;
    for (i = (A.length - 1) / 2; i >= 1; i--) {
      heapSortHelper(A, i, A.length - 1);
    }
    for (i = A.length - 1; i >= 2; i--) {
      swap(A, i, 1);
      heapSortHelper(A, 1, i - 1);
    }
  }

  private static void heapSortHelper(Orderable A[], int i, int m) {
    int j;
    while (2 * i <= m) {
      j = 2 * i;
      if (j < m) {
        if (A[j].less(A[j + 1])) {
          j = j + 1;
        }
      }
      if (A[i].less(A[j])) {
        swap(A, i, j);
        i = j;
      } else {
        i = m;
      }
    }
  }
}
