package eu.lilithmonodia.javasorts;

import eu.lilithmonodia.javasorts.sorts.SortingAlgorithm;
import eu.lilithmonodia.javasorts.sorts.impracticalsorts.PancakeSort;
import eu.lilithmonodia.javasorts.sorts.practicalsorts.HeapSort;
import eu.lilithmonodia.javasorts.sorts.practicalsorts.MergeSort;
import eu.lilithmonodia.javasorts.sorts.practicalsorts.QuickSort;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The JavaFXSortsMain class is the main class for the Sorting Algorithms application. It extends the Application class
 * provided by JavaFX and implements the start() method to create the user interface and handle user interactions.
 */
public class JavaFXSortsMain extends Application {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private final List<SortingAlgorithm> sortingAlgorithms = Arrays.asList(
            new PancakeSort(),
            new QuickSort(),
            new MergeSort(),
            new HeapSort()
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
    public void start(Stage primaryStage) {
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

            int listLength;
            try {
                listLength = Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                outputLabel.setText("Invalid input!");
                return;
            }

            spinner.setVisible(true);
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() {
                    List<Integer> list = SortingAlgorithm.generateRandomList(listLength);
                    StringBuilder outputSb = new StringBuilder();
                    for (SortingAlgorithm sortingAlgorithm : sortingAlgorithms) {
                        sortingAlgorithm.displayAndTime(list, sortingAlgorithm.getClass().getSimpleName(), outputSb);
                    }

                    Platform.runLater(() -> {
                        Stage newWindow = createNewWindow(outputSb.toString());
                        newWindow.show();
                        spinner.setVisible(false);
                    });
                    return null;
                }
            };
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
    private Stage createNewWindow(String outputSb) {
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
}