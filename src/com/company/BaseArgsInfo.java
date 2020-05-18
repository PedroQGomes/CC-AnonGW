package com.company;

import java.util.ArrayList;
import java.util.List;

public class BaseArgsInfo {
    private static BaseArgsInfo instance = null;
    private List<String> peers;
    private String target_ip;
    private String port;

    private BaseArgsInfo() {
        peers = new ArrayList<>();
    }

    public static BaseArgsInfo getInstance() {
        if(instance == null) instance = new BaseArgsInfo();
        return instance;
    }

    public List<String> getPeers() {
        return peers;
    }

    public void setPeers(List<String> peers) {
        this.peers = peers;
    }

    public void addPeer(String peer) {
        this.peers.add(peer);
    }

    public String getTarget_ip() {
        return target_ip;
    }

    public void setTarget_ip(String target_ip) {
        this.target_ip = target_ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
