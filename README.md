## JavaSorts Program

The JavaSorts program is a command-line application that allows the user to generate a random list of integers and apply
various sorting algorithms to it. The performance of each algorithm is timed, and the program logs the execution time of
each algorithm to a database.

### Core Classes

- `JavaSortsMain`: This is the main class of the JavaSorts program. It initializes a `SortingAlgorithmFactory` to create
  different sorting algorithm objects, receives the user's input, performs the sorting tasks using multiple algorithms,
  and logs the results in a database.

- `SortingAlgorithm`: An abstract class that represents a sorting algorithm. This class contains an abstract `sort`
  method to be implemented by any concrete sorting algorithm classes. This class also provides a helper
  method, `generateRandomList`, to generate a random list of integers, and a `displayAndTime` method to display and time
  the execution of the sorting algorithm.

### How To Use

1. Run the `main` method in the `JavaSortsMain` class.
2. The program will prompt for the desired length of the list to sort.
3. Enter a positive integer for the length of the list, or "q" to quit the program.
4. The program will generate a random list of the specified length, use the various sorting algorithms to sort the list,
   and log the result.
5. The loop will continue until you choose to quit by entering "q".

### Available Sorting Algorithms

The following sorting algorithms are available in this program.

- Practical Sorting Algorithms:
    - HeapSort
    - InsertionSort
    - MergeSort
    - QuickSort
    - RadixSort
    - SelectionSort
    - ShellSort
    - TimSort
- Impractical Sorting Algorithms:
    - BogoSort
    - BozoSort
    - PancakeSort
    - SlowSort
    - StoogeSort
    - BubbleSort

### Note

- The main application makes use of `java.util.concurrent.ExecutorService` to concurrently perform multiple sorting
  tasks.
- Each sorting operation logs to a database using `DatabaseLogger`.
- Logging is also done using Java's native `java.util.logging.Logger`