package sorts;

import orderable.OrderableProfile;

public class RadixSort extends SortAlgorithm {
  // length of the keys
  public static int l;
  private static final int m = 10;

  // based on Ottman, Widmayer, 2017, pages 125 - 126
  // make sure that l is set before calling
  public static void sort(OrderableProfile[] A) {
    OrderableProfile[] b = new OrderableProfile[A.length];
    int[] c = new int[10];
    int i, j, t;

    for(t = 0; t < l; t++) {
      for(i = 0; i < m; i++) {
        c[i] = 0;
      }
      for(i = 1; i < A.length; i++) {
        j = A[i].digit(t);
        c[j] = c[j] + 1;
      }
      c[m - 1] = A.length - c[m - 1];
      for(i = 2; i <= m; i++) {
        c[m - i] = c[m - i + 1] - c[m - i];
      }
      for(i = 1; i < A.length; i++) {
        j = A[i].digit(t);
        b[c[j]] = A[i];
        c[j] = c[j] + 1;
      }
      for(i = 1; i < A.length; i++) {
        A[i] = b[i];
      }
    }
  }

  // determines greatest occurring key-length (number of decimal-digits) among elements in A
  public static int findKeyLength(OrderableProfile[] A) {
    int longest = 0, currentLength = 0;

    for(int i = 0; i < A.length; i++) {
      if(A[i].key == 0) {
        continue;
      }

      currentLength = (int) (Math.log10(A[i].key) + 1);
      if(currentLength > longest) {
        longest = currentLength;
      }
    }

    return longest;
  }
}
