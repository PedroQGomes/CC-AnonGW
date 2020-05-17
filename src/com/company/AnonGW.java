package com.company;

import com.company.models.ListenTcp;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import com.company.Proxy;

public class AnonGW {

        public static void main(String[] args) throws IOException {
            //List<String> pears = new ArrayList<>();

            //AnonBD agw = new AnonBD("127.0.0.0",pears,"10.3.3.1");
            ServerSocket server = new ServerSocket(100);
            //Socket out = new Socket("www.sapo.pt",80);
            //System.out.println(out.isConnected());
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

            //Thread listenUdp = new Thread(new ListenUdp(agw));
           // listenUdp.start();

        }

        public static void startThread(Connection connection) {
            Thread t = new Thread(connection);
            t.start();
        }


}
