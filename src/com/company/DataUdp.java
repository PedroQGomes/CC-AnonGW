package com.company;

import java.io.Serializable;

public class DataUdp implements Serializable {
    // classe que vai ter a data com o pedido do cliente mais o overhead que adicionarmos
    private int idsession; // id de sessao que unico em relaçao ao ip
    //mecanismo que assugura a ordem dos pacostes
    private String data;//dados


    public DataUdp(int id,String d){
        idsession = id;
        data = d;

    }



}
