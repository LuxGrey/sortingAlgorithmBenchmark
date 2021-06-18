package sorts;

import orderable.Orderable;

public class MergeSort extends SortAlgorithm {

  public static void sort(Orderable A[]) {
    mergeSortHelper(A, 1, A.length - 1);
  }

  private static void mergeSortHelper(Orderable A[], int l, int r) {
    if (r > l) {
      int m = (l + r) / 2;
      mergeSortHelper(A, l, m);
      mergeSortHelper(A, m + 1, r);
      merge(A, l, m, r);
    }
  }

  private static void naturalMergeSort(Orderable A[], int l, int r) {
    int ll = 1, mm, rr;
    do {
      rr = l - 1;
      while(rr < r) {
        ll = rr + 1;
        mm = ll;
        while(mm < r && A[mm+1].greaterEqual(A[mm])) {
          mm = mm + 1;
        }
        if(mm < r) {
          rr = mm + 1;
          while(rr < r && A[rr+1].greaterEqual(A[rr])) {
            rr = rr + 1;
          }
          merge(A, ll, mm, rr);
        }
        else {
          rr = mm;
        }
      }
    } while(ll == l);
  }

  private static void merge(Orderable A[], int l, int m, int r) {
    Orderable B[] = new Orderable[A.length];
    int i = l;
    int j = m + 1;
    int k = l;
    while (i <= m && j <= r) {
      if (A[i].less(A[j])) {
        B[k] = A[i];
        i++;
      } else {
        B[k] = A[j];
        j++;
      }
      k++;
    }
    if (i > m) {
      for (int h = j; h <= r; h++, k++) {
        B[k] = A[h];
      }
    } else {
      for (int h = i; h <= m; h++, k++) {
        B[k] = A[h];
      }
    }
    for (int h = l; h <= r; h++) {
      A[h] = B[h];
    }
  }
}
