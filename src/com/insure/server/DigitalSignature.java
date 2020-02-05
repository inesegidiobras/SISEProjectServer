package com.insure.server;

import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class DigitalSignature {

    public DigitalSignature(){

    }

    public static String Sign(String content, String filename) throws Exception {
        EncryptPrivate encrypt=new EncryptPrivate();
        String msg=content;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String hash= Base64.getEncoder().encodeToString(digest.digest(msg.getBytes("UTF-8")));
        PrivateKey privKey = encrypt.getPrivate(filename);
        return encrypt.encryptText(hash, privKey);
    }

    public boolean verifyDigitalSignature(String content, String encryptedHash, String filename) throws Exception {
        String msg1=content;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String hash1= Base64.getEncoder().encodeToString(digest.digest(msg1.getBytes("UTF-8")));
        DecryptPublic decrypt=new DecryptPublic();
        String msg2=encryptedHash;
        PublicKey pubKey = decrypt.getPublic(filename);
        String hash2= decrypt.decryptText(msg2, pubKey);
        if (hash1==hash2){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        DigitalSignature signature = new DigitalSignature();
        String i = signature.Sign("Inês Egídio dos Santos Brás", "Keys\\Client1PrivateKey");
        System.out.println(i);
    }
}
