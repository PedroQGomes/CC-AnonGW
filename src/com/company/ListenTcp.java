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
        ServerSocket server = new ServerSocket(100);
        while(true) {
         try {
        Socket in = server.accept();
        System.out.println(in.getLocalAddress());
        startThread(new Connection(in,"www.google.pt",80));
        
        } catch(Exception e) {
        e.printStackTrace();
        }finally {
        }
    }


    
    public static void startThread(Connection connection) {
        Thread t = new Thread(connection);
        t.start();
    }

    /*private void listen() throws IOException{
        InetAddress address = InetAddress.getByName("127.0.0.0");
        ServerSocket sv = new ServerSocket(12345);
        while(true){
            // aceitar coneçoes tcp dos clientes
            Socket client = sv.accept();
            WorkerTcp worker = new WorkerTcp(client, agw);
            Thread t = new Thread(worker);
            t.start();

        }
    } */

    
}
