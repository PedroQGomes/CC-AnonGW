package com.company;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

public class UDPConnection implements Runnable {
    private DatagramSocket socket;
    private InetAddress address;
    private int UDPconnPort;
    public UDPConnection() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while(address == null) {
            try {
                address = InetAddress.getByName(BaseArgsInfo.getInstance().getRandomPeer());
            } catch (UnknownHostException e) {
            }
        }
    }

    @Override
    public void run() {
        byte[] buf = null;
        UDPPortMessage udpPortMessage = new UDPPortMessage();
        buf = ObjectSerializer.getObjectInByte(udpPortMessage);
        UDPconnPort = udpPortMessage.getCustomPort();
        DatagramPacket portPacket = new DatagramPacket(buf,buf.length,address,6666);
        try {
            socket.send(portPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        DatagramPacket packet = new DatagramPacket(buf,buf.length,address,udpPortMessage.getCustomPort());
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } */

    }
}
