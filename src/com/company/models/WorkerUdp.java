package com.company.models;

import com.company.AnonBD;
import com.company.DataUdp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class WorkerUdp implements Runnable {
    private DatagramPacket packetUdp;
    private AnonBD anonBD;

    public WorkerUdp(DatagramPacket p, AnonBD b){
        this.packetUdp = p;
        this.anonBD = b;

    }

    @Override
    public void run() {
        InetAddress ip = this.packetUdp.getAddress();
        byte[] data = this.packetUdp.getData();
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        DataUdp udpdata;
        ObjectInput in = null;
        //ler os dados do array de bites para o objeto DataUdp
            try {
                in = new ObjectInputStream(bis);
                udpdata = (DataUdp) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {//nobody cares sobre a excessao de fechar
            }
        }
        //ja temos os dados na instancia udpdata, next trata-los



    }
}
