package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public  class ListenUdp implements Runnable {
    private AnonBD agw;


    public ListenUdp(AnonBD a) {
        this.agw = a;

    }


    @Override
    public void run() {
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void listen() throws IOException {
        byte[] buf = new byte[256];
        DatagramSocket socket = new DatagramSocket(4445);
        while(true){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

        }
    }


}