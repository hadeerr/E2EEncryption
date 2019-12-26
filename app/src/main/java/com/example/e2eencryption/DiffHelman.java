package com.example.e2eencryption;

import android.util.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;

public class DiffHelman {
    public DiffHelman() {
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    public static String encrypt(byte[] message, SecretKey secretKey) throws Exception {
        return AESCipher.encrypt(message, secretKey.getEncoded()) ;//);
    }

    public static String decrypt(byte[] message, SecretKey secretKey) throws Exception {
            return AESCipher.decrypt(message, secretKey.getEncoded());

    }

    public static SecretKey combine(PrivateKey receiver, PublicKey sender) throws Exception {
        KeyAgreement ka = KeyAgreement.getInstance("DH");
        ka.init(receiver);
        ka.doPhase(sender, true);
        SecretKey secretKey = ka.generateSecret("AES");
        return secretKey;
    }
}