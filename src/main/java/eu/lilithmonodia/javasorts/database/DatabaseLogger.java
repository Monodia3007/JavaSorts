package eu.lilithmonodia.javasorts.database;

import eu.lilithmonodia.javasorts.configuration.Configuration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * The DatabaseLogger class is responsible for connecting to a PostgreSQL database
 * and adding log entries to it.
 */
public class DatabaseLogger {

    // Create the Logger
    private static final Logger LOG = Logger.getLogger(DatabaseLogger.class.getName());

    private static final String INSERT_LOG = "INSERT INTO log_entries(sorting_time, list_size, algorithm) VALUES (?, ?, ?)";

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @return the Connection object representing the connection to the database, or null if an error occurs.
     */
    private Connection connect() {
        Connection conn = null;
        try {
            Configuration cfg = Configuration.fromConfig();
            conn = DriverManager.getConnection(cfg.databaseHost(), cfg.databaseUser(), cfg.userPassword());
        } catch (SQLException | IOException e) {
            LOG.warning(e.getMessage());
        }
        return conn;
    }

    /**
     * Adds a log entry to the database.
     *
     * @param sortingTime the time taken for the sorting process
     * @param listSize    the size of the list being sorted
     * @param algorithm   the algorithm used for sorting
     */
    public void addLog(String sortingTime, int listSize, String algorithm) {
        try (Connection conn = this.connect()) {
            if (conn == null) {
                LOG.warning("Connection to database could not be established");
                return;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(INSERT_LOG)) {
                pstmt.setString(1, sortingTime);
                pstmt.setInt(2, listSize);
                pstmt.setString(3, algorithm);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.warning(e.getMessage());
        }
    }
}
