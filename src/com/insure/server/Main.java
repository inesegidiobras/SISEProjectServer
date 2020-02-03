package com.insure.server;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String args[]){
        System.out.println("Claim Web Service is starting.");
        ClaimDataStore claimDB = new ClaimDataStore();
        Endpoint.publish("http://localhost:8090/claimservice", claimDB);
    }
}
