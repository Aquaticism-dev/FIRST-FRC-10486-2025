package org.weavers.simulation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for communication between the Java program and a Godot program to debug before the bot is finished.
 * Uses TCP to connect.
 */
public class SimulationMain {
    private static final int PORT = 9999;
    private static final Logger logger = Logger.getLogger(SimulationMain.class.getName());

    public static void main(String[] args) {
        setupLogger();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Client connected: " + clientSocket.getInetAddress());

                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server error: ", e);
        }
    }

    private static void setupLogger() {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
    }
}
