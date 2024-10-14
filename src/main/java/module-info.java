/**
 * The `eu.lilithmonodia.javasorts` module definition.
 * <p>
 * This module includes the following dependencies:
 * <ul>
 * <li>`com.google.gson`: For JSON parsing and serialization.</li>
 * <li>`java.sql`: For SQL database access.</li>
 * <li>`javafx.controls`: For JavaFX user interface controls.</li>
 * <li>`javafx.graphics`: For JavaFX graphics support.</li>
 * <li>`org.apache.commons.io`: For Apache Commons IO utilities.</li>
 * <li>`org.jetbrains.annotations`: For JetBrains annotations.</li>
 * </ul>
 * This module exports the `eu.lilithmonodia.javasorts` package.
 */
module eu.lilithmonodia.javasorts {
    requires com.google.gson;
    requires java.sql;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.apache.commons.io;
    requires org.jetbrains.annotations;

    exports eu.lilithmonodia.javasorts;
    exports eu.lilithmonodia.javasorts.sorts;
}