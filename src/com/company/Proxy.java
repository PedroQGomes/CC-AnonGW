package com.company;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.System.Logger;
import java.net.Socket;
import java.net.SocketException;

public class Proxy implements Runnable {

    private final Socket in;
    private final Socket out;
    //private final Logger logger = Logger.getLogger(Proxy.class.getName());

    public Proxy( Socket in,  Socket out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
       // LOGGER.info("Proxy {}:{} --> {}:{}", in.getInetAddress().getHostName(), in.getPort(),out.getInetAddress().getHostName(), out.getPort());
        try {
             InputStream inputStream = getInputStream();
             OutputStream outputStream = getOutputStream();
            if (inputStream == null || outputStream == null) {
                System.out.println("ESTOU A NUL");
                return;
            }

            byte[] reply = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(reply)) != -1) {
                System.out.println(reply);
                outputStream.write(reply, 0, bytesRead);
            }

        } catch (SocketException ignored) {
            ignored.printStackTrace();
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private InputStream getInputStream() {
        try {
            return in.getInputStream();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private OutputStream getOutputStream() {
        try {
            return out.getOutputStream();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
}