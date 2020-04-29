package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnonBD {

    // todos os pacotes no tunel udp usam a porta 6666 logo temos de saber a que conecao tcp é que pertencem

    /*fase inicial

    tem de ter um server socket a dar listen de clientes
    ter registos de :
        identificar de sessao
        cliente que mandou o pedido
        saber para onde esta a mandar
        qnts pacotes ja recebeu do cliente
        qnts pacotes ja enviou
        <>

    */

    private List<Socket> logs;
    private String myIp;
    private List<String> overlay_pears;
    private String targetSvIp;

    public AnonBD(String ip, List<String> parceiros, String sv){
        myIp = ip;
        logs = new ArrayList<>();
        overlay_pears = new ArrayList<>(parceiros);
        targetSvIp = sv;
    }


    public String getTargetSvIp(){
        return targetSvIp;
    }

    public void addClient(Socket s){
        this.logs.add(s);

    }





}
