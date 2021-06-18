import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import orderable.MeasuringResult;
import orderable.Orderable;
import orderable.OrderableProfile;
import sorts.BubbleSort;
import sorts.HeapSort;
import sorts.InsertionSort;
import sorts.MergeSort;
import sorts.QuickSort;
import sorts.RadixSort;
import sorts.SelectionSort;
import sorts.ShellSort;

public class Main {

  private static final String[] SORTS = {"SelectionSort", "InsertionSort", "ShellSort",
      "BubbleSort", "QuickSort", "HeapSort", "MergeSort", "RadixSort"};
  private static final String SPECIAL_RADIX_SORT = "RadixSortWithDiscovery";
  private static final int[] SIZES = {1000, 10000, 20000, 30000, 40000, 50000};
  private static final int[] KEY_RANGES = {100, 100000, 100000000};
  private static final int REPETITIONS = 10;

  private static Map<String, MeasuringResult[][]> measuringResults = new HashMap<>();

  public static void main(String[] args) {
    System.out.println("Initialising measuringResults...");
    initialiseMap();
    System.out.println("Finished initialising measuringResults");

    for (int i = 0; i < SIZES.length; i++) {
      for (int j = 0; j < KEY_RANGES.length; j++) {
        for (int k = 0; k < REPETITIONS; k++) {
          OrderableProfile[] generatedData = DataGenerator
              .generateTestData(SIZES[i], 1, KEY_RANGES[j]);
          for (String s : SORTS) {
            runAlgorithm(generatedData, s, i, j);
          }
        }
        System.out.println("Combination size=" + SIZES[i] + ", key-range=" + KEY_RANGES[j] + " done");
      }
    }

    System.out.println("Writing results to CSV-file...");
    writeResultsToCSV();
    System.out.println("Finished writing results to file");
  }

  private static void initialiseMap() {
    // ergänze um RadixSortWithLengthDiscovery
    String[] sortsExtended = new String[SORTS.length + 1];
    System.arraycopy(SORTS, 0, sortsExtended, 0, SORTS.length);
    sortsExtended[sortsExtended.length - 1] = SPECIAL_RADIX_SORT;

    for (String s : sortsExtended) {
      MeasuringResult[][] temp = new MeasuringResult[SIZES.length][KEY_RANGES.length];
      for (int i = 0; i < SIZES.length; i++) {
        for (int j = 0; j < KEY_RANGES.length; j++) {
          temp[i][j] = new MeasuringResult(SIZES[i], KEY_RANGES[j]);
        }
      }

      measuringResults.put(s, temp);
    }
  }

  private static void runAlgorithm(OrderableProfile[] dataToSort, String sortingAlgorithm,
      int sizesIndex, int keyRangesIndex) {
    OrderableProfile[] workingCopy = copyArray(dataToSort);
    Date preSort;
    Date postSort;
    Date preFindKeyLength;

    if (sortingAlgorithm.equals("SelectionSort")) {
      preSort = new Date();
      SelectionSort.sort(workingCopy);
      postSort = new Date();
    } else if (sortingAlgorithm.equals("InsertionSort")) {
      preSort = new Date();
      InsertionSort.sort(workingCopy);
      postSort = new Date();
    } else if (sortingAlgorithm.equals("ShellSort")) {
      preSort = new Date();
      ShellSort.sort(workingCopy);
      postSort = new Date();
    } else if (sortingAlgorithm.equals("BubbleSort")) {
      preSort = new Date();
      BubbleSort.sort(workingCopy);
      postSort = new Date();
    } else if (sortingAlgorithm.equals("QuickSort")) {
      preSort = new Date();
      QuickSort.sort(workingCopy);
      postSort = new Date();
    } else if (sortingAlgorithm.equals("HeapSort")) {
      preSort = new Date();
      HeapSort.sort(workingCopy);
      postSort = new Date();
    } else if (sortingAlgorithm.equals("MergeSort")) {
      preSort = new Date();
      MergeSort.sort(workingCopy);
      postSort = new Date();
    } else if (sortingAlgorithm.equals("RadixSort")) {
      preFindKeyLength = new Date();
      RadixSort.l = RadixSort.findKeyLength(workingCopy);
      preSort = new Date();
      RadixSort.sort(workingCopy);
      postSort = new Date();

      ((MeasuringResult[][]) measuringResults.get(SPECIAL_RADIX_SORT))[sizesIndex][keyRangesIndex]
          .addMeasuredTime(postSort.getTime() - preFindKeyLength.getTime());
    } else {
      return;
    }

    if (!verify(workingCopy)) {
      System.err.println("Data are not sorted!"
          + "\nAlgorithm: " + sortingAlgorithm + ", Size: " + workingCopy.length
          + ", Key-range: " + KEY_RANGES[keyRangesIndex]);
      System.exit(1);
    }

    ((MeasuringResult[][]) measuringResults.get(sortingAlgorithm))[sizesIndex][keyRangesIndex]
        .addMeasuredTime(postSort.getTime() - preSort.getTime());
  }

  private static OrderableProfile[] copyArray(OrderableProfile[] source) {
    return Arrays.copyOf(source, source.length);
  }

  private static boolean verify(Orderable[] dataToVerify) {
    for (int i = 1; i < dataToVerify.length; i++) {
      if (dataToVerify[i].less(dataToVerify[i - 1])) {
        return false; // nicht sortiert
      }
    }

    return true;
  }

  private static void writeResultsToCSV() {
    try (PrintWriter writer = new PrintWriter(new File("sorting_algorithm_measurements.csv"))) {
      StringBuilder sb = new StringBuilder();

      // write table head
      sb.append("sorting algorithm,sorted data amount,key range,time spent sorting (ms)\n");

      // write data
      for(Map.Entry<String, MeasuringResult[][]> entry : measuringResults.entrySet()) {
        String algorithm = entry.getKey();
        MeasuringResult[][] results2d = entry.getValue();
        for (MeasuringResult[] results1d : results2d) {
          for (MeasuringResult result : results1d) {
            sb.append(algorithm + "," + result.getSortedDataAmount() + ","
                + result.getKeyRange() + "," + result.getMeasuredTime() + "\n");
          }
        }
      }

      writer.write(sb.toString());
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }
}
