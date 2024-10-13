/**
 * Provides classes for sorting algorithms and the main entry points for the sorting application.
 *
 * <p>The package consists of several key classes:</p>
 * <ul>
 *   <li>{@link eu.lilithmonodia.javasorts.Launcher}: Main entry point of the application. Determines
 *       whether to run the application in console mode or GUI mode based on command-line arguments.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.JavaSortsMain}: Executes sorting tasks using various sorting algorithms
 *       in console mode, and manages user inputs and interactions through the console.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.JavaFXSortsMain}: Implements the JavaFX GUI for the sorting application,
 *       enabling users to interact with the application through a graphical interface.</li>
 *   <li>{@link eu.lilithmonodia.javasorts.SortingAlgorithmFactory}: Responsible for creating instances of different
 *       sorting algorithms and providing a method to retrieve a specific sorting algorithm based on its type.</li>
 * </ul>
 *
 * <p>The supported sorting algorithms include both practical sorts like QuickSort, MergeSort, and HeapSort,
 * as well as impractical sorts such as BogoSort and SlowSort.</p>
 *
 * <p>Examples of how to use the package:
 * <pre>
 *     // Run the application in console mode
 *     java -jar "file-name".jar --console
 *
 *     // Run the application in GUI mode
 *     java -jar "file-name".jar
 * </pre>
 * </p>
 */
package eu.lilithmonodia.javasorts;