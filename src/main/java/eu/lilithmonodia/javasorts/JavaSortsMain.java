package eu.lilithmonodia.javasorts;

import eu.lilithmonodia.javasorts.database.DatabaseLogger;
import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
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
 * The JavaSortsMain class is a command-line program that allows the user to generate a random list of integers
 * and apply various sorting algorithms to it. The program displays the execution time of each algorithm and logs
 * it to a database.
 */
public class JavaSortsMain {

    private static final Logger LOGGER = Logger.getLogger(JavaSortsMain.class.getName());
    private static final long TIMEOUT = 300;  // consider to adjust this value
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private static final DatabaseLogger DB_LOGGER = new DatabaseLogger();

    /**
     * The main method is the entry point of the program. It initializes a SortingAlgorithmFactory
     * to create sorting algorithm objects, stores the names of the algorithms in an array, and
     * executes a loop to repeatedly prompt the user for input. It generates a random list of
     * integers based on the user's input, performs sorting tasks using various algorithms, and
     * logs the result. The loop continues until the user enters -1. After the loop ends, it
     * prints a termination message and exits the program.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        SortingAlgorithmFactory sortingAlgorithmFactory = new SortingAlgorithmFactory();
        String[] algorithmNames = {"QuickSort", "MergeSort", "HeapSort", "PancakeSort", "BogoSort", "SlowSort", "BubbleSort", "SelectionSort", "InsertionSort", "BozoSort", "StoogeSort", "StalinSort", "ShellSort", "RadixSort"};

        while (true) {
            int listLength = getListLengthFromUserInput();

            if (listLength == -1) break;

            List<Integer> list = generateRandomList(listLength);
            performAndLogSortingTasks(list, algorithmNames, sortingAlgorithmFactory);
        }

        System.out.println("Program has been terminated.");
        exit(0);
    }

    /**
     * This method prompts the user to enter the desired length of a list.
     * The user can enter a number or 'q' to quit the program. If the user
     * enters 'q', the method returns -1 to indicate program termination.
     * If the user enters a number, it is parsed as an integer and returned
     * as the list length.
     *
     * @return the desired length of the list, or -1 if the user chooses to quit
     */
    private static int getListLengthFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        int listLength;

        while (true) {
            System.out.println("Enter the desired length of the list or 'q' to quit: ");
            String userInput = scanner.next();

            // End the program if user inputs 'q'
            if (userInput.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                listLength = Integer.parseInt(userInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        return listLength;
    }

    /**
     * This method performs sorting tasks on a given list using multiple sorting algorithms.
     * It creates a fixed thread pool with a number of threads equal to the number of algorithm names provided.
     * For each algorithm name, it submits a sorting task to the thread pool.
     * Each sorting task creates a separate copy of the list and performs sorting using the specified algorithm.
     * The sorting task logs the sorted list along with the algorithm name.
     *
     * @param list the list to be sorted
     * @param algorithmNames an array of algorithm names to be used for sorting
     * @param sortingAlgorithmFactory a factory object that provides instances of sorting algorithms
     */
    private static void performAndLogSortingTasks(List<Integer> list, String[] algorithmNames, SortingAlgorithmFactory sortingAlgorithmFactory) {
        ExecutorService executor = Executors.newFixedThreadPool(algorithmNames.length);

        List<Future<?>> futures = new ArrayList<>();
        for (String name : algorithmNames) {
            Future<?> future = executor.submit(() -> {
                List<Integer> listCopy = new ArrayList<>(list); // separate copy for each sort
                sortAndLog(listCopy, name, sortingAlgorithmFactory);
            });

            futures.add(future);
        }

        executor.shutdown();
        waitForTasksCompletion(futures);
        System.out.println("All tasks have completed execution.");
    }

    /**
     * This method performs sorting on a given list using a specified sorting algorithm.
     * It logs the sorted list along with the algorithm name and duration to the database and prints a log message.
     *
     * @param list the list to be sorted
     * @param name the name of the sorting algorithm to be used
     * @param sortingAlgorithmFactory a factory object that provides instances of sorting algorithms
     */
    private static void sortAndLog(List<Integer> list, String name, SortingAlgorithmFactory sortingAlgorithmFactory) {
        StringBuilder outputSb = new StringBuilder();
        SortingAlgorithm algorithm = sortingAlgorithmFactory.getSortingAlgorithm(name);
        algorithm.displayAndTime(list, name, outputSb);

        String formattedDuration = extractTimeFromOutput(outputSb.toString());
        DB_LOGGER.addLog(formattedDuration, list.size(), name);  // Log to database

        LOGGER.log(Level.INFO, "Sorting with " + name + " algorithm on list of size " + list.size() + " took " + formattedDuration);
    }

    /**
     * This method extracts the duration from the output string.
     * The output string should contain the duration formatted as "took <duration>".
     *
     * @param output the output string from which to extract the duration
     * @return the extracted duration, or "Time not available" if no duration is found in the output string
     */
    private static String extractTimeFromOutput(String output) {
        Pattern pattern = Pattern.compile("took (.*)");
        Matcher matcher = pattern.matcher(output);
        return (matcher.find()) ?  matcher.group(1) : "Time not available";
    }

    /**
     * This method waits for the completion of a list of tasks represented by Futures.
     * <p>
     * It iterates over each Future in the given list and waits for the task to complete.
     * If the task is interrupted or encounters an exception during execution, an error
     * message is logged using the LOGGER with Level.SEVERE.
     * If the task exceeds the specified timeout, a separate error message is logged.
     *
     * @param futures the list of Futures representing the tasks to wait for
     */
    private static void waitForTasksCompletion(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get(TIMEOUT, UNIT);
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.log(Level.SEVERE, "Task execution was interrupted or failed.", e);
            } catch (TimeoutException e) {
                LOGGER.log(Level.SEVERE, "Task execution exceeded the time limit.");
            }
        }
    }
}