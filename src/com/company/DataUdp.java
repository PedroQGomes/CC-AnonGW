package com.company;

import java.io.Serializable;
import java.util.Arrays;

public class DataUdp implements Serializable {
    // classe que vai ter a data com o pedido do cliente mais o overhead que adicionarmos
    private int idsession; // id de sessao que unico em relaçao ao ip
    //mecanismo que assugura a ordem dos pacostes
    private byte data[];//dados


    public DataUdp(int id,byte d[],int bytesUsed){
        idsession = id;
        data = Arrays.copyOf(d,bytesUsed);
    }

    public int getIdsession() {
        return idsession;
    }

    public void setIdsession(int idsession) {
        this.idsession = idsession;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
