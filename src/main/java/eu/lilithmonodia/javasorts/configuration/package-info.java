/**
 * Provides classes and records for configuration management, including reading configuration details from a JSON file.
 *
 * <p>This package includes the necessary classes to manage configurations required by other parts of the application,
 * such as establishing database connections. The configurations are typically read from a JSON file named `config.json`.</p>
 *
 * <p>Key class in this package:</p>
 * <ul>
 *   <li>{@link eu.lilithmonodia.javasorts.configuration.Configuration}: A record that holds the necessary details
 *       required to establish a database connection, such as the host address, database username, and password.
 *       It provides a method to read these details from a JSON configuration file named `config.json`.</li>
 * </ul>
 *
 * <p>Examples of how to use the package:
 * <pre>
 *     {@code
 *     // Reading configuration from config.json
 *     try {
 *         Configuration config = Configuration.fromConfig();
 *         String databaseHost = config.databaseHost();
 *         String databaseUser = config.databaseUser();
 *         String userPassword = config.userPassword();
 *
 *         // Use these details to establish a database connection or other operations
 *     } catch (IOException e) {
 *         e.printStackTrace(); // Handle exception
 *     }
 *     }
 * </pre>
 * </p>
 */
package eu.lilithmonodia.javasorts.configuration;