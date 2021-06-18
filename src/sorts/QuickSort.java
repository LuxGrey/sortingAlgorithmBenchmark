package sorts;

import orderable.Orderable;

public class QuickSort extends SortAlgorithm {

  public static void sort(Orderable A[]) {
    quickSortHelper(A, 1, A.length - 1);
  }

  private static void quickSortHelper(Orderable A[], int l, int r) {
    int i, j;
    Orderable pivot;
    if (r > l) {
      i = l - 1;
      j = r;
      pivot = A[r];

      while (true) {
        do {
          i++;
        } while (!A[i].greaterEqual(pivot));
        do {
          j--;
        } while (!A[j].lessEqual(pivot));
        if (i >= j) {
          break;
        }
        swap(A, i, j);
      }
      swap(A, i, r);
      quickSortHelper(A, l, i - 1);
      quickSortHelper(A, i + 1, r);
    }
  }
}
