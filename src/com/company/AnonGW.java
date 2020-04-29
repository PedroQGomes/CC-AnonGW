package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AnonGW {

        public static void main(String[] args) throws IOException {
            List<String> pears = new ArrayList<>();

            AnonBD agw = new AnonBD("127.0.0.0",pears,"10.3.3.1");
            InetAddress address = InetAddress.getByName("127.0.0.0");
            ServerSocket sv = new ServerSocket(12345,50,address);

            while(true){

                Socket client = sv.accept();
                Worker worker = new Worker(client,agw);
                Thread t = new Thread(worker);
                t.start();



            }

        }
}
