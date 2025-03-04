package org.weavers.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles TCP client connection.
 * It just works.
 */
class ClientHandler extends Thread {
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("");
            String message;
            while (true) {
                message = in.readLine();
                if (message == null) {
                    break;
                }
                logger.info("Received: " + message);
                String[] split = message.split("\\|");
                String response = handleCommand(split[0], split[1]);
                out.println(response);

                if (message.equalsIgnoreCase("DISCONNECT")) {
                    break;
                }
                sleep(10);
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Client disconnected: " + socket.getInetAddress(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
                logger.info("Connection closed: " + socket.getInetAddress());
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error closing socket: ", e);
            }
        }
    }

    private String handleCommand(String command, String message) {
        switch (command.toUpperCase()) {
            case "ECHO":
                return message;
        }
        return "";
    }
}
