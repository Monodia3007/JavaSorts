package eu.lilithmonodia.javasorts;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Launcher class serves as the entry point for the application. It handles command-line arguments
 * to determine whether to run the application in console mode or GUI mode.
 * <p>
 * The following command-line arguments are supported:
 * <ul>
 * <li>--help: Displays usage information.</li>
 * <li>--console: Runs the application in console mode.</li>
 * </ul>
 */
public class Launcher {
    private static final Logger LOGGER = Logger.getLogger(Launcher.class.getName());

    /**
     * Entry point for the application. Handles command-line arguments to determine
     * the mode of operation, either console or GUI mode.
     *
     * @param args Command-line arguments. Valid options are:
     *             <ul><li>--help: Displays usage information.</li>
     *             <li>--console: Runs the application in console mode.</li>
     *             <li>If no arguments are passed, the application runs in GUI mode.</li></ul>
     */
    public static void main(String @NotNull [] args) {
        if (args.length >= 1 && args[0].equals("--help")) {
            LOGGER.log(Level.INFO, "Usage: java -jar <file-name>.jar --console or java -jar <file-name>.jar");
        } else if (args.length >= 1 && args[0].equals("--console")) {
            JavaSortsMain.main(args);
        } else if (args.length == 0) {
            JavaFXSortsMain.main(args);
        } else {
            LOGGER.log(Level.SEVERE, "Use java -jar <file-name>.jar --help");
        }
    }
}
