package com.insure.server;

import java.sql.Timestamp;

public class Document {

    private final int id_document;
    private Timestamp timeStamp;
    private final int id_user;
    private String content;

    public Document(int id_document, Timestamp timeStamp, int id_user, String content) {
        this.id_document=id_document;
        this.timeStamp = timeStamp;
        this.id_user = id_user;
        this.content=content;
    }
    public Timestamp getTimeStamp() {
        return this.timeStamp; }

    public void setDateStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;

    }
    public int getId_user() {
        return id_user;
    }
    public String getContent() {
        return content;
    }
}
