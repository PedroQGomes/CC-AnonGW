package com.company.models;

import com.company.AnonBD;
import com.company.WorkerTcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenTcp implements Runnable {

    private AnonBD agw;
    public ListenTcp(AnonBD a){
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


    private void listen() throws IOException{
        InetAddress address = InetAddress.getByName("127.0.0.1");
        ServerSocket sv = new ServerSocket(12345,50,address);
        while(true){
            // aceitar coneçoes tcp dos clientes
            Socket client = sv.accept();
            WorkerTcp worker = new WorkerTcp(client, agw);
            Thread t = new Thread(worker);
            t.start();

        }
    }
}
