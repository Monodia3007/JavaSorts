/**
 * Provides classes for database interactions, including logging sorting results into a PostgreSQL database.
 *
 * <p>This package includes classes and methods to handle database connections and operations needed to log the
 * details of sorting operations, such as the time taken, the size of the sorted list, and the algorithm used.</p>
 *
 * <p>Key class in this package:</p>
 * <ul>
 *   <li>{@link eu.lilithmonodia.javasorts.database.DatabaseLogger}: Responsible for connecting to the PostgreSQL
 *       database and adding log entries with details about the sorting operations. It includes methods for
 *       establishing a database connection and adding log entries into the database table.</li>
 * </ul>
 * <p>
 * Examples of how to use the package:
 * <pre>
 *     {@code
 *     // Create an instance of DatabaseLogger
 *     DatabaseLogger dbLogger = new DatabaseLogger();
 *
 *     // Log sorting details to the database
 *     long rawSortingTime = 123456789L; // in nanoseconds
 *     String formattedSortingTime = "123.45 ms";
 *     int listSize = 100;
 *     String algorithm = "QuickSort";
 *
 *     dbLogger.addLog(rawSortingTime, formattedSortingTime, listSize, algorithm);
 *     }
 * </pre>
 */
package eu.lilithmonodia.javasorts.database;