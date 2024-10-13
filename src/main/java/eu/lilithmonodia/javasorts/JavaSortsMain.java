package eu.lilithmonodia.javasorts;

import eu.lilithmonodia.javasorts.database.DatabaseLogger;
import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static eu.lilithmonodia.javasorts.sorts.SortingAlgorithm.generateRandomList;
import static java.lang.System.exit;

/**
 * The main class responsible for executing sorting tasks using different sorting algorithms.
 */
public class JavaSortsMain {
    private static final Logger LOGGER = Logger.getLogger(JavaSortsMain.class.getName());
    private static final long TIMEOUT = 120;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private static final DatabaseLogger DB_LOGGER = new DatabaseLogger();
    private static final int REPEATS = 50;

    /**
     * The main method of the program. This method is responsible for executing the sorting tasks using different
     * sorting algorithms. It prompts the user for the length of the list, generates a random list of that length, and
     * performs the sorting tasks using the sorting algorithm factory. It continues to prompt the user for the length of
     * the list until the user enters -1, at which point the program terminates.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SortingAlgorithmFactory sortingAlgorithmFactory = new SortingAlgorithmFactory();
        String[] algorithmNames = {"QuickSort", "MergeSort", "HeapSort", "PancakeSort",
                "SlowSort", "BubbleSort", "SelectionSort", "InsertionSort", "StoogeSort",
                "ShellSort", "RadixSort", "TimSort", "ParallelMergeSort"};

        while (true) {
            long listLength = getListLengthFromUserInput();

            if (listLength == -1) break;

            List<Integer> list = generateRandomList(listLength);
            performAndLogSortingTasks(list, algorithmNames, sortingAlgorithmFactory);
        }

        LOGGER.log(Level.INFO, "Program has been terminated.");
        exit(0);
    }

    /**
     * Prompts the user to enter the desired length of a list.
     *
     * @return The length of the list provided by the user, or -1 if the user chooses to quit.
     */
    private static long getListLengthFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            LOGGER.log(Level.INFO, "Enter the desired length of the list or 'q' to quit: ");
            String userInput = scanner.next();

            if (userInput.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                return Long.parseLong(userInput);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.INFO, "Invalid input! Please enter a number.");
            }
        }
    }

    /**
     * Executes sorting tasks using different sorting algorithms and logs the execution of each task.
     *
     * @param list                    the list of integers to be sorted
     * @param algorithmNames          an array of strings representing the names of the sorting algorithms to be used
     * @param sortingAlgorithmFactory the factory object used to create instances of sorting algorithms
     */
    private static void performAndLogSortingTasks(List<Integer> list, String @NotNull [] algorithmNames, SortingAlgorithmFactory sortingAlgorithmFactory) {
        ExecutorService executor = Executors.newFixedThreadPool(algorithmNames.length);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < REPEATS; i++) {
            for (String name : algorithmNames) {
                List<Integer> listCopy = new ArrayList<>(list);
                SortingAlgorithm algorithm = sortingAlgorithmFactory.getSortingAlgorithm(name);
                StringBuilder outputSb = new StringBuilder();
                StringBuilder rawDuration = new StringBuilder();

                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    algorithm.displayAndTime(listCopy, name, outputSb, rawDuration);
                    handleSingleFuture(name, listCopy.size(), outputSb, rawDuration);
                }, executor);

                futures.add(future);
            }
        }

        try {
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            allFutures.get(TIMEOUT, UNIT);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Execution was interrupted.", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException | TimeoutException e) {
            LOGGER.log(Level.SEVERE, "Execution failed or timed out.", e);
        } finally {
            executor.shutdown();
        }

        LOGGER.log(Level.INFO, "All tasks have completed execution.");
    }

    /**
     * Extracts the time information from the given output string.
     *
     * @param output the output string from which to extract the time information
     * @return the extracted time information if found, or "Time not available" if not found
     */
    private static String extractTimeFromOutput(String output) {
        Pattern pattern = Pattern.compile("took (.*)");
        Matcher matcher = pattern.matcher(output);
        return matcher.find() ? matcher.group(1) : "Time not available";
    }

    /**
     * Handles the completion of a single Future task.
     *
     * @param name        the name of the task
     * @param listSize    the size of the list
     * @param outputSb    the StringBuilder containing the output
     * @param rawDuration the StringBuilder containing the raw duration
     */
    private static void handleSingleFuture(String name, int listSize, @NotNull StringBuilder outputSb, @NotNull StringBuilder rawDuration) {
        try {
            logElapsedTime(name, listSize, outputSb.toString(), Long.parseLong(rawDuration.toString()));
        } catch (Exception e) {
            logException(name);
        }
    }

    /**
     * Logs the elapsed time for a sorting operation on a list.
     *
     * @param name        the algorithm name used for sorting
     * @param listSize    the size of the list being sorted
     * @param output      the output containing the formatted duration of the sorting operation
     * @param rawDuration the raw duration of the sorting operation
     */
    private static void logElapsedTime(String name, int listSize, String output, long rawDuration) {
        String formattedDuration = extractTimeFromOutput(output);

        if (!"Time not available".equals(formattedDuration)) {
            LOGGER.log(Level.INFO, "Sorting with {0} algorithm on list of size {1} took {2}",
                    new Object[]{name, listSize, formattedDuration});
            DB_LOGGER.addLog(rawDuration, formattedDuration, listSize, name);
        }
    }

    /**
     * Logs an exception with an appropriate message.
     *
     * @param taskName the name of the task that was interrupted or failed
     */
    private static void logException(String taskName) {
        LOGGER.log(Level.SEVERE, "The execution of the {0} was interrupted or failed.", new Object[]{taskName});
    }
}