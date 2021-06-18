package sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import orderable.Orderable;

public class ShellSort extends SortAlgorithm {

  public static void sort(Orderable A[]) {
    List<Integer> sequence = generateIncrementSequence(A.length);
    int h, i, j, s;
    Orderable temp;
    boolean cont;

    for (s = sequence.size() - 1; s >= 0; s--) {
      h = sequence.get(s);
      for (i = h + 1; i < A.length; i++) {
        j = i;
        temp = A[i];
        cont = true;
        while (A[j - h].greater(temp) && cont) {
          A[j] = A[j - h];
          j = j - h;
          cont = (j > h);
        }
        A[j] = temp;
      }
    }
  }

  // generiert eine absteigende Sequenz aller 3-glatten Zahlen kleiner als n
  private static List<Integer> generateIncrementSequence(int n) {
    List<Integer> sequence = new ArrayList<>();
    sequence.add(1);
    int i2 = 0;
    int i3 = 0;

    while (true) {
      int n2 = 2 * sequence.get(i2);
      int n3 = 3 * sequence.get(i3);
      int next = Math.min(n2, n3);
      if (next >= n) {
        break;
      }
      sequence.add(next);
      if (n2 <= n3) {
        i2++;
      }
      if (n2 >= n3) {
        i3++;
      }
    }

    Collections.reverse(sequence.subList(0, sequence.size()));
    return sequence;
  }
}
