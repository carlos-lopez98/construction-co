package com.solvd.constructionco.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

        private static final int POOL_SIZE = 10;

        private String url;
        private String username;
        private String password;

        private List<Connection> connectionPool;

        //Creates
        public ConnectionPool() {
            connectionPool = new ArrayList<>();
            retrieveProperties();

            for (int i = 0; i < POOL_SIZE; i++) {
                try {
                    Connection connection = createConnection();
                    connectionPool.add(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        private void retrieveProperties() {
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream("src/main/resources/database.properties")) {
                properties.load(fis);
                url = properties.getProperty("url");
                username = properties.getProperty("username");
                password = properties.getProperty("password");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private Connection createConnection() throws SQLException {
            return DriverManager.getConnection(url, username, password);
        }
        //Added a timeout of 5 secs
        public synchronized Connection getConnection() {
            long timeout = 5000;
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;

            //Waiting up to 5 secs
            while (connectionPool.isEmpty() && elapsedTime < timeout) {
                try {
                wait(timeout - elapsedTime);
                } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while waiting for a connection", e);
                 }
            elapsedTime = System.currentTimeMillis() - startTime;
         }

            if (connectionPool.isEmpty()) {
                throw new IllegalStateException("Error no connection available after 5 secs");
            }

            //returns one connection
            return connectionPool.remove(connectionPool.size() - 1);
        }

        public void releaseConnection(Connection connection) {
             connectionPool.add(connection);
        }
    }

