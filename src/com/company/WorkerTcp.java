package com.company;

import java.io.*;
import java.net.*;
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
        try {
            BufferedReader brClient = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter printClient = new PrintWriter(cliente.getOutputStream());

            String linha;
            while((linha = brClient.readLine()) != null && !linha.equals("quit")){
                sendPacketToPear(linha);


                linha = brClient.readLine();

            }


        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    private void sendPacketToPear(String data){ // trata de mandar o pedido do cliente pelo canal UDP
        InetAddress GwPear = null;
        DataUdp dataUdp = null;
        try {
            GwPear =  this.gw.getRandomPear();
            int id = this.gw.creatNewUdpCon(data,this.gw.getMyIpAdress()); // ao criar um registo novo mete o ip do pear a quem vai enviar? ou o proprio ip? - neste momento esta o proprio ip
            dataUdp = new DataUdp(id,data); // cria o pacote de dados a ser enviado
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if(dataUdp == null)return;
        if(GwPear == null)return;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(dataUdp);
            out.flush();
            byte[] dataInBytes = bos.toByteArray();
            //manda o pacote
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(dataInBytes, dataInBytes.length, GwPear, 6666);
            socket.send(packet);



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }


    }



}