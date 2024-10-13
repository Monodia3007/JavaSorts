/**
 * Provides utility functions to support the main sorting application.
 *
 * <p>This package includes classes that offer reusable methods and constants
 * that can be utilized across different parts of the sorting application. The utilities
 * are designed to handle common tasks that are not specific to any single sorting algorithm
 * or part of the main application logic.</p>
 *
 * <p>Key utility class in this package:</p>
 * <ul>
 *   <li>{@link eu.lilithmonodia.javasorts.util.TimeUtils}: A utility class for formatting durations
 *       in nanoseconds into a human-readable string representation. It includes methods to handle
 *       time-related operations and is intended to be used in situations where precise and
 *       understandable time data is required.</li>
 * </ul>
 *
 * <p>Examples of how to use the package:
 * <pre>{@code
 *     // Formatting a duration in nanoseconds
 *     long durationInNanos = 1234567890L;
 *     String formattedDuration = TimeUtils.formatNanos(durationInNanos);
 *     System.out.println(formattedDuration); // Output might be: "1.23 s"
 *     }
 * </pre>
 * </p>
 */
package eu.lilithmonodia.javasorts.util;