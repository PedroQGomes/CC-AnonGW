package com.company;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class Connection implements Runnable {

    private final Socket clientsocket;
    private Socket serverConnection = null;

   // private static final Logger LOGGER = LoggerFactory.getLogger(Connection.class);

    public Connection(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    @Override
    public void run() {
       // LOGGER.info("new connection {}:{}", clientsocket.getInetAddress().getHostName(), clientsocket.getPort());
        try {
            BaseArgsInfo tmp = BaseArgsInfo.getInstance();
            serverConnection = new Socket(tmp.getTarget_ip(), tmp.getPort());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

       // LOGGER.info("Proxy {}:{} <-> {}:{}", clientsocket.getInetAddress().getHostName(), clientsocket.getPort(), serverConnection.getInetAddress().getHostName(), serverConnection.getPort());

        new Thread(new Proxy(clientsocket, serverConnection)).start();
        new Thread(new Proxy(serverConnection, clientsocket)).start();
        new Thread(() -> {
            while (true) {
                if (clientsocket.isClosed()) {
                    System.out.println("client socket ({}:{}) closed" + clientsocket.getInetAddress().getHostName() + clientsocket.getPort());
                    closeServerConnection();
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    private void closeServerConnection() {
        if (serverConnection != null && !serverConnection.isClosed()) {
            try {
               System.out.println("closing remote host connection {}:{} " + serverConnection.getInetAddress().getHostName() + serverConnection.getPort());
                serverConnection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}