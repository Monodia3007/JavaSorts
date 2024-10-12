package eu.lilithmonodia.javasorts;

import eu.lilithmonodia.javasorts.database.DatabaseLogger;
import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import eu.lilithmonodia.javasorts.sorts.impracticalsorts.*;
import eu.lilithmonodia.javasorts.sorts.practicalsorts.*;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The JavaFXSortsMain class is the main class for the Sorting Algorithms application. It extends the Application class
 * provided by JavaFX and implements the start() method to create the user interface and handle user interactions.
 */
public class JavaFXSortsMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(JavaFXSortsMain.class.getName());
    private static final long TIMEOUT = 120; // Timeout in seconds
    private static final DatabaseLogger DB_LOGGER = new DatabaseLogger();

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final List<SortingAlgorithm> sortingAlgorithms = Arrays.asList(
            new QuickSort(),
            new MergeSort(),
            new HeapSort(),
            new InsertionSort(),
            new ShellSort(),
            new SelectionSort(),
            new TimSort(),
            new RadixSort(),
            new PancakeSort(),
            new BogoSort(),
            new SlowSort(),
            new BubbleSort(),
            new BozoSort(),
            new StoogeSort()
    );

    /**
     * The main entry point for the application.
     *
     * @param args The command line arguments passed to the main method.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application by setting up the main window and handling user input.
     *
     * @param primaryStage The primary stage representing the main window of the application.
     */
    @Override
    public void start(@NotNull Stage primaryStage) {
        primaryStage.setTitle("Sorting Algorithms");

        TextField inputField = new TextField();
        inputField.setPromptText("Enter a number...");
        inputField.setMaxWidth(200);

        Button button = new Button("Sort");
        button.setStyle("-fx-background-color: darkcyan; -fx-text-fill: white; -fx-font-size: 13pt;");

        Label outputLabel = new Label();
        outputLabel.setWrapText(true);
        outputLabel.setStyle("-fx-font-size: 12pt;");

        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setVisible(false);

        VBox vbox = new VBox(10, inputField, button, spinner, outputLabel); // 10 is the spacing between elements
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: azure; -fx-padding: 20;");

        button.setOnAction(e -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                outputLabel.setText("Input field is empty!");
                return;
            }

            long listLength;
            try {
                listLength = Long.parseLong(input);
            } catch (NumberFormatException exception) {
                outputLabel.setText("Invalid input!");
                return;
            }

            spinner.setVisible(true);
            Task<String> task = createSortingTask(listLength);

            task.setOnSucceeded(event -> {
                Stage newWindow = createNewWindow(task.getValue());
                newWindow.show();
                spinner.setVisible(false);
            });

            task.setOnFailed(event -> {
                outputLabel.setText("Sorting task failed or timed out.");
                spinner.setVisible(false);
            });

            executorService.submit(task);
        });

        vbox.setPrefWidth(350);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a new window to display the sorting results.
     *
     * @param outputSb The output string to be displayed in the window.
     * @return The newly created stage for the new window.
     */
    private @NotNull Stage createNewWindow(String outputSb) {
        Label resultLabel = new Label(outputSb);
        resultLabel.setWrapText(true);
        resultLabel.setStyle("-fx-font-family: monospace; -fx-font-size: 12pt; -fx-padding: 10;");

        ScrollPane scrollPane = new ScrollPane(resultLabel);
        scrollPane.setFitToWidth(true);

        VBox vBox = new VBox(scrollPane);
        vBox.setStyle("-fx-background-color: #333; -fx-padding: 10;");
        vBox.setPrefWidth(600);

        Scene secondScene = new Scene(vBox);

        Stage newWindow = new Stage();
        newWindow.setTitle("Sorting Results");
        newWindow.setScene(secondScene);

        return newWindow;
    }

    /**
     * Creates a task to perform sorting using multiple sorting algorithms.
     *
     * @param listLength The length of the list to be sorted.
     * @return A Task that performs the sorting.
     */
    @Contract(value = "_ -> new", pure = true)
    private @NotNull Task<String> createSortingTask(long listLength) {
        return new Task<>() {
            @Override
            protected String call() {
                List<Integer> list = SortingAlgorithm.generateRandomList(listLength);

                return sortingAlgorithms.stream().map(algorithm -> {
                    List<Integer> listCopy = new ArrayList<>(list);
                    StringBuilder outputSb = new StringBuilder();
                    StringBuilder rawDuration = new StringBuilder();

                    Future<?> future = executorService.submit(() -> algorithm.displayAndTime(listCopy, algorithm.getClass().getSimpleName(), outputSb, rawDuration));
                    try {
                        future.get(TIMEOUT, TimeUnit.SECONDS);
                        logElapsedTime(algorithm.getClass().getSimpleName(), list.size(), outputSb.toString(), Long.parseLong(rawDuration.toString()));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Re-interrupt the thread
                        handleTimeoutOrException(e, algorithm.getClass().getSimpleName());
                        future.cancel(true);
                    } catch (ThreadDeath td) {
                        future.cancel(true);
                        throw td; // Propagate ThreadDeath
                    } catch (ExecutionException | TimeoutException e) {
                        handleTimeoutOrException(e, algorithm.getClass().getSimpleName());
                        future.cancel(true);
                    }

                    return outputSb.toString();
                }).collect(Collectors.joining("\n"));
            }
        };
    }

    /**
     * Logs the elapsed time for a sorting operation on a list.
     *
     * @param name     the algorithm name used for sorting
     * @param listSize the size of the list being sorted
     * @param output   the output containing the formatted duration of the sorting operation
     */
    private void logElapsedTime(String name, int listSize, String output, long rawDuration) {
        String formattedDuration = extractTimeFromOutput(output);

        if (!"Time not available".equals(formattedDuration)) {
            LOGGER.log(Level.INFO, "Sorting with {0} algorithm on list of size {1} took {2}",
                    new Object[]{name, listSize, formattedDuration});
            DB_LOGGER.addLog(rawDuration, formattedDuration, listSize, name);
        }
    }

    /**
     * Handles timeouts or exceptions during sorting.
     *
     * @param e        The exception encountered
     * @param taskName The name of the sorting algorithm
     */
    private void handleTimeoutOrException(Throwable e, String taskName) {
        if (e instanceof TimeoutException) {
            LOGGER.log(Level.WARNING, "The sorting with {0} algorithm timed out.", taskName);
        } else {
            LOGGER.log(Level.SEVERE, "The sorting with {0} algorithm failed.", taskName);
        }
    }

    /**
     * Extracts the time information from the given output string.
     *
     * @param output the output string from which to extract the time information
     * @return the extracted time information if found, or "Time not available" if not found
     */
    private String extractTimeFromOutput(String output) {
        Pattern pattern = Pattern.compile("took (.*)");
        Matcher matcher = pattern.matcher(output);
        return matcher.find() ? matcher.group(1) : "Time not available";
    }
}