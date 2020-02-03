package com.insure.server;

import java.sql.Timestamp;

public class Document {

    private final int id_document;
    private Timestamp timeStamp;

    public Document(int id_document, Timestamp timeStamp) {
        this.id_document=id_document;
        this.timeStamp = timeStamp;
    }
    public Timestamp getTimeStamp() {
        return this.timeStamp; }

    public void setDateStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;

    }
}
