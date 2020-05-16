package com.company;

import com.company.models.WorkerUdp;

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
        DatagramSocket socket = new DatagramSocket(6666);
        while(true){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            Thread work = new Thread(new WorkerUdp(packet,this.agw));
            work.start();

        }
    }


}