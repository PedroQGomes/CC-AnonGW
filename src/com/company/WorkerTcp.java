package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class WorkerTcp implements Runnable {
    private Socket cliente; // isto é o socket tcp do cliente
    private AnonBD gw;         // base de dados com os registos


    public WorkerTcp(Socket c, AnonBD g){
        cliente = c;
        gw = g;

    }


    @Override
    public void run() {
        this.gw.addClient(cliente);
        String sv = gw.getTargetSvIp();
        try {
            Socket svSocket = new Socket(sv, 12345);
            BufferedReader brClient = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter printClient = new PrintWriter(cliente.getOutputStream());

            BufferedReader brServer = new BufferedReader(new InputStreamReader(svSocket.getInputStream()));
            PrintWriter printServer = new PrintWriter(svSocket.getOutputStream());

            String linha;
            while((linha = brClient.readLine()) != null && !linha.equals("quit")){
                printServer.println(linha);

                linha = brServer.readLine();


                printClient.println(linha);

                linha = brClient.readLine();

            }


        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}