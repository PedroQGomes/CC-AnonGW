package com.company;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class UDPPortMessage implements Serializable {
    private int customPort;
    private String cliente;
    public UDPPortMessage() {
        customPort =  getRandomPort();
    }

    public int getCustomPort() {
        return customPort;
    }


    public int getRandomPort() { //TODO: LISTA DE PORTAS EM USO
        int random = ThreadLocalRandom.current().nextInt(7000,8000);
        return random;
    }
}
