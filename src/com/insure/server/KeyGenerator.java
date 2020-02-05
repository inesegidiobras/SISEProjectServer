package com.insure.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Scanner;

public class KeyGenerator {

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public void setKeyGen(KeyPairGenerator keyGen) {
        this.keyGen = keyGen;
    }

    public void setPair(KeyPair pair) {
        this.pair = pair;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public KeyPairGenerator getKeyGen() {
        return keyGen;
    }

    public KeyPair getPair() {
        return pair;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public KeyGenerator(KeyPairGenerator keyGen, KeyPair pair, PrivateKey privateKey, PublicKey publicKey) {
        this.keyGen = keyGen;
        this.pair = pair;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    private void createKeys() throws NoSuchAlgorithmException {
        this.pair=this.keyGen.generateKeyPair();
        this.privateKey=this.pair.getPrivate();
        this.publicKey=this.pair.getPublic();
    }

    public KeyGenerator(int keyLength) throws NoSuchAlgorithmException{
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
    }
    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();


        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public static void main(String args[]) {
        KeyGenerator gk;

        System.out.println("Enter the name of the user you want to generate the keys: ");
        Scanner in=new Scanner(System.in);
        String user=in.nextLine();

        try{
            gk=new KeyGenerator(1024);
            gk.createKeys();
            gk.writeToFile("Keys/"+user+"PublicKey", gk.getPublicKey().getEncoded());
            gk.writeToFile("Keys/"+user+"PrivateKey", gk.getPrivateKey().getEncoded());
            System.out.println("Keys are generated.");
        }catch (NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}

