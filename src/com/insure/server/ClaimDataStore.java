package com.insure.server;

import javax.jws.WebService;
import java.security.*;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebService
public class ClaimDataStore {

    private Claim claim;
    private String description;
    private boolean isEligible;
    private boolean isAccepted;
    private int id_user;
    private AtomicInteger id_claim;
    private String encryptedHash;
    private String content;
    ConcurrentHashMap<Integer, Claim> DataStore;
    private Object DigitalSignature;

    public ClaimDataStore() {
        DataStore = new ConcurrentHashMap<Integer, Claim>();
        AtomicInteger id_claim = new AtomicInteger(0);
    }

    public synchronized void createClaim(Integer id_user, String description) {
        id_claim.incrementAndGet();
        int id = id_claim.intValue();
        Claim claim1 = new Claim(id_user, description, id, false, false);
        DataStore.put(id, claim1);
    }

    public Claim getClaimByID(int id_claim) {
        return this.DataStore.get(id_claim);
    }

    public void addDocClaim(AtomicInteger id_document, Timestamp timestamp, String content, int id_claim, int id_user, int filename) throws Exception {
        DigitalSignature signature= new DigitalSignature();
        boolean validation = signature.verifyDigitalSignature(content, encryptedHash, "Keys\\" + "Client" + id_user + "PublicKey");
        if (validation){
            DataStore.get(id_claim).createDocument(content , id_claim, encryptedHash);
        }
        else {
            throw new Exception();
        }
    }

    public synchronized void changeEligibility(int id_claim) {
        if (this.DataStore.containsKey(id_claim)) {
            Claim claim = getClaimByID(id_claim);
            claim.changeEligibility();
        } else {
            System.out.println("This claim does not exist");
        }
    }

    public boolean isEligible(int id) {
        Claim claim = getClaimByID(id);
        return claim.isEligible();
    }

    public synchronized boolean changeAcceptance(int id_claim) {
        Claim claim = getClaimByID(id_claim);
        if (claim.isEligible) {
            claim.changeAcceptance();
        } else {
            System.out.println("This claim is not Eligible!");
        }
        return claim.isAccepted;
    }

    public int size(){
        return DataStore.size();
    }

    public static void main(String[] args) throws ClaimNotFoundException {
        ClaimDataStore DB=new ClaimDataStore();
        DB.createClaim(1,"oi");
        System.out.println(DB.size());

    }
}
