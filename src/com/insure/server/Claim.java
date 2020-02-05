package com.insure.server;

import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class Claim {

    private int id_user;
    private String description;
    private int id_claim;
    boolean isEligible;
    boolean isAccepted;
    private String content;
    private String encryptedHash;
    ConcurrentHashMap<Integer, Document> ClaimDocument;
    AtomicInteger id_document=new AtomicInteger(0);

    Claim(int id_user, String description, int id_claim, boolean isEligible, boolean isAccepted) {
        this.id_user = id_user;
        this.description = description;
        this.id_claim = id_claim;
        this.isEligible = isEligible;
        this.isAccepted = isAccepted;
        ClaimDocument = new ConcurrentHashMap<Integer, Document>();
    }

    public synchronized void createDocument(String content, int id_claim, String encryptedHash) {
        id_document.incrementAndGet();
        int id=id_document.intValue();
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        Document document1= new Document(id, timeStamp, id_user, content);
        ClaimDocument.put(id,document1);
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void changeEligibility(boolean isEligible) {
        this.isEligible=!isEligible();
    }

    public boolean isAccepted() {
        return this.isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted=!isAccepted();
    }

    public int getId_user() {
        return this.id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_claim() {
        return this.id_claim;
    }

    public void setId_claim(int id_claim) {
        this.id_claim = id_claim;
    }

    public void changeEligibility() {
        this.isEligible = !isEligible();
    }

    public void changeAcceptance() {
        this.isAccepted = !isAccepted();
    }

    public String getContent() {
        return content;
    }

    public String getEncryptedHash() {
        return encryptedHash;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEncryptedHash(String encryptedHash) {
        this.encryptedHash = encryptedHash;
    }

    public String getClaimStatus() {
        String E;
        String A;

        if (this.isEligible()) {
            E = "Eligible";
        } else {
            E = "Not Eligible";
        }
        if (this.isAccepted()) {
            A = "Accepted";
        } else {
            A = "Not Accepted";
        }

        return ("ID_user: " + this.getId_user() + " | description: " + this.getDescription() + " | id_claim: "
                + this.getId_claim() + " | Date:" + " | " + E + " | " + A);
    }
};

