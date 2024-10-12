package eu.lilithmonodia.javasorts;

import org.jetbrains.annotations.NotNull;

public class Launcher {
    public static void main(String @NotNull [] args) {
        if (args.length == 0) {
            JavaFXSortsMain.main(args);
        } else if (args.length == 1 && args[0].equals("console")) {
            JavaSortsMain.main(args);
        }
    }
}
