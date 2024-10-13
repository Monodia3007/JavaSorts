/**
 * Java module descriptor for the eu.lilithmonodia.javasorts module.
 * <p>
 * This module requires several dependencies for its functionality:
 * <ul>
 * <li>Requires com.google.gson – for working with JSON data.</li>
 * <li>Requires java.sql – for JDBC API to access and process data stored
 *                      in relational databases.</li>
 * <li>Requires javafx.controls – for JavaFX user interface controls.</li>
 * <li>Requires javafx.graphics – for JavaFX graphics capabilities.</li>
 * <li>Requires org.apache.commons.io – for utilities to help with IO operations.</li>
 * <li>Requires org.jetbrains.annotations – for JetBrains' annotations like @NotNull, @Nullable, etc.</li>
 * </ul>
 */
module eu.lilithmonodia.javasorts {
    requires com.google.gson;
    requires java.sql;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.apache.commons.io;
    requires org.jetbrains.annotations;
}