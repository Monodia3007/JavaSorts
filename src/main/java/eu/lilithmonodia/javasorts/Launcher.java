package eu.lilithmonodia.javasorts;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Launcher class of the project used to access every aspect of the project
 */
public class Launcher {
    private static final Logger LOGGER = Logger.getLogger(Launcher.class.getName());

    /**
     * The main method of the program used to access the two sides of the project
     *
     * @param args argument of the program
     */
    public static void main(String @NotNull [] args) {
        if (args.length >= 1 && args[0].equals("--help")) {
            LOGGER.log(Level.INFO ,"Usage: java -jar <file-name>.jar --console or java -jar <file-name>.jar");
        } else if (args.length >= 1 && args[0].equals("--console")) {
            JavaSortsMain.main(args);
        } else if (args.length == 0) {
            JavaFXSortsMain.main(args);
        } else {
            LOGGER.log(Level.SEVERE, "Use java -jar <file-name>.jar --help");
        }
    }
}
