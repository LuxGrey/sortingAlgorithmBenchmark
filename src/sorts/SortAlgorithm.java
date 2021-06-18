package sorts;

import orderable.Orderable;

public abstract class SortAlgorithm {

  public static void swap(Object A[], int i, int j) {
    Object o = A[i];
    A[i] = A[j];
    A[j] = o;
  }

  public static void sort(Orderable A[]) {
  }

  public static void printArray(Orderable A[]) {
    for (int i = 1; i < A.length; i++) {
      System.out.print(A[i].toString() + " ");
    }
    System.out.println();
  }
}
