package com.company;

import com.company.models.ListenTcp;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class AnonGW {

        public static void main(String[] args) throws IOException {
            List<String> pears = new ArrayList<>();

            AnonBD agw = new AnonBD("127.0.0.0",pears,"10.3.3.1");


            Thread listenTcp = new Thread(new ListenTcp(agw));
            listenTcp.start();

            Thread listenUdp = new Thread(new ListenUdp(agw));
            listenUdp.start();

        }




}
