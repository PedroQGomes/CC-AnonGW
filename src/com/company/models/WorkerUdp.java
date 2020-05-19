package com.company.models;

import com.company.AnonBD;
import com.company.DataUdp;
import com.company.ObjectSerializer;

import javax.xml.crypto.Data;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class WorkerUdp implements Runnable {
    private int port;
    private AnonBD anonBD;
    private DatagramSocket socket;
    private List<DataUdp> dataUdps;
    public WorkerUdp(int port, AnonBD b){
        this.port = port;
        this.anonBD = b;
        try {
            this.socket = new DatagramSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataUdps = new ArrayList<>();
    }

    @Override
    public void run() { //TODO: LOGICA DE RECEBER PACOTES SO NA PORTA DO WORKER OU SEJA DE UM SÓ CLIENTE E DE FAZER O TUNNELING DA APP TODA, OU SEJA CLIENTE -> TargetSERVER , TargetServer->Cliente
        while(true) { //TODO: BREAK QND O PACOTE DE TCP JA FOI ENVIADO
            byte[] tmp = new byte[256];
            DatagramPacket packet = new DatagramPacket(tmp,tmp.length);
            try {
                socket.receive(packet);
                dataUdps.add((DataUdp) (ObjectSerializer.getObjectFromByte(packet.getData())));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
