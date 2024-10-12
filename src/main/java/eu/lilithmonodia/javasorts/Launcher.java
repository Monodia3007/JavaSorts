package eu.lilithmonodia.javasorts;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher {
    private static final Logger LOGGER = Logger.getLogger(Launcher.class.getName());

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
