package eu.lilithmonodia.javasorts;

import eu.lilithmonodia.javasorts.database.DatabaseLogger;
import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

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

public class JavaSortsMain {
    private static final Logger LOGGER = Logger.getLogger(JavaSortsMain.class.getName());
    private static final long TIMEOUT = 120;  // consider to adjust this value
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private static final DatabaseLogger DB_LOGGER = new DatabaseLogger();

    /**
     * The main method of the program.
     * This method is responsible for executing the sorting tasks using different sorting algorithms.
     * It prompts the user for the length of the list, generates a random list of that length,
     * and performs the sorting tasks using the sorting algorithm factory.
     * It continues to prompt the user for the length of the list until the user enters -1, at which point the program terminates.
     *
     * @param args the command-line arguments

     */
    public static void main(String[] args) {
        SortingAlgorithmFactory sortingAlgorithmFactory = new SortingAlgorithmFactory();
        String[] algorithmNames = {"QuickSort", "MergeSort", "HeapSort", "PancakeSort", "BogoSort",
                "SlowSort", "BubbleSort", "SelectionSort", "InsertionSort", "BozoSort", "StoogeSort",
                "ShellSort", "RadixSort", "TimSort"};

        while (true) {
            int listLength = getListLengthFromUserInput();

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
    private static int getListLengthFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        int listLength;

        while (true) {
            LOGGER.log(Level.INFO, "Enter the desired length of the list or 'q' to quit: ");
            String userInput = scanner.next();

            if (userInput.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                listLength = Integer.parseInt(userInput);
                break;
            } catch (NumberFormatException e) {
                LOGGER.log(Level.INFO, "Invalid input! Please enter a number.");
            }
        }

        return listLength;
    }

    /**
     * Executes sorting tasks using different sorting algorithms and logs the execution of each task.
     *
     * @param list                    the list of integers to be sorted
     * @param algorithmNames          an array of strings representing the names of the sorting algorithms to be used
     * @param sortingAlgorithmFactory the factory object used to create instances of sorting algorithms
     */
    private static void performAndLogSortingTasks(List<Integer> list, String[] algorithmNames, SortingAlgorithmFactory sortingAlgorithmFactory) {
        ExecutorService executor = Executors.newFixedThreadPool(algorithmNames.length);

        List<Future<?>> futures = new ArrayList<>();
        for (String name : algorithmNames) {
            Future<?> future = executor.submit(() -> {
                List<Integer> listCopy = new ArrayList<>(list);
                sortAndLog(listCopy, name, sortingAlgorithmFactory);
            });
            futures.add(future);
        }

        executor.shutdown();
        waitForTasksCompletion(futures);
        LOGGER.log(Level.INFO, "All tasks have completed execution.");
    }

    /**
     * Sorts the given list using the specified sorting algorithm and logs the duration of the sorting process.
     *
     * @param list                     the list of integers to be sorted
     * @param name                     the name of the sorting algorithm to be used
     * @param sortingAlgorithmFactory  the factory object used to create the sorting algorithm
     */
    private static void sortAndLog(List<Integer> list, String name, SortingAlgorithmFactory sortingAlgorithmFactory) {
        StringBuilder outputSb = new StringBuilder();
        SortingAlgorithm algorithm = sortingAlgorithmFactory.getSortingAlgorithm(name);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> algorithm.displayAndTime(list, name, outputSb));

        try {
            future.get(JavaSortsMain.TIMEOUT, JavaSortsMain.UNIT);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Preserve interrupt status
            LOGGER.log(Level.SEVERE, "The execution of the {0} algorithm was interrupted or failed.", new Object[]{name});
        } catch (ExecutionException e) {
            LOGGER.log(Level.SEVERE, "The execution of the {0} algorithm was interrupted or failed.", new Object[]{name});
        } catch (TimeoutException e) {
            LOGGER.log(Level.SEVERE, "The execution of the {0} algorithm exceeded the time limit.", new Object[]{name});
        } finally {
            executor.shutdown();
        }

        String formattedDuration = extractTimeFromOutput(outputSb.toString());

        if (!"Time not available".equals(formattedDuration)) {
            LOGGER.log(Level.INFO, "Sorting with {0} algorithm on list of size {1} took {2}",
                    new Object[]{name, list.size(), formattedDuration});
            DB_LOGGER.addLog(formattedDuration, list.size(), name);
        }
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
     * Waits for the completion of a list of Future tasks.
     *
     * @param futures the list of Future tasks to wait for completion
     */
    private static void waitForTasksCompletion(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get(TIMEOUT, UNIT);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Preserve interrupt status
                LOGGER.log(Level.SEVERE, "Task execution was interrupted or failed.", e);
            } catch (ExecutionException e) {
                LOGGER.log(Level.SEVERE, "Task execution was interrupted or failed.", e);
            } catch (TimeoutException e) {
                LOGGER.log(Level.SEVERE, "Task execution exceeded the time limit.", e);
            }
        }
    }
}