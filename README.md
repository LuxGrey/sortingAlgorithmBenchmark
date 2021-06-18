# sortingAlgorithmBenchmark
This project serves as a benchmark to test the performance of some sorting-algorithms implemented in Java.
It covers the following sorting-algorithms:
- BubbleSort
- HeapSort
- InsertionSort
- MergeSort
- QuickSort
- RadixSort
- SelectionSort
- ShellSort

All implementations of sorting-algorithms and a couple other things are based on chapter 2 of the book "Algorithmen und Datenstrukturen" by Thomas Ottmann and Peter Widmayer, 6th edition, 2017. (ISBN 9783662556504)

## How to run
Just run the program without call-arguments and wait for it to finish. This can take anywhere around 20 minutes.

## What does it measure?
The program measures the time that a sorting-algorithm requires to sort an unsorted array of custom data-objects.  
It does this with varrying parameters; these are
- the size of the input-array
- the value-range for the generated numerical key-values

## How does it measure that?
An instance of ``java.util.Date`` will be constructed both right before and right after each call to a sort-function to capture a timestamp at creation.
Afterwards ``Date.getTime()`` is called to get their Unix time in milliseconds and the difference between the two timestamps is then saved as the duration of the respective sorting-process. To handle fluctuations a little, each combination of input-array-size and key-value-range is run 10 times for every sorting-algorithm and an average time is calculated from these 10 runs.

## Where can I see the benchmark results?
If the program runs and exits normally, it will have writen the results to a local .csv-file after all sorting-processes were finished.

## How do I adjust the program to measure with more/different parameters?
If you want to adjust the input-array-sizes or key-value-ranges, then that can be easily done by updating the respective constants at the top of the ``Main``-class.
Anything else you will have to figure out on your own.
