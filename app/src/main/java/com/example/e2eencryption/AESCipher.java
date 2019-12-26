package com.example.e2eencryption;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESCipher {

    public AESCipher() {
    }


    public  static String encrypt(byte[] input, byte[] key) throws Exception {
        String message1= Base64.encodeToString(input,Base64.DEFAULT);
        SecretKey secretKey = new SecretKeySpec(key, 0, key.length, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(message1.getBytes());
        String encrypted2=Base64.encodeToString(encrypted, Base64.DEFAULT);
        return encrypted2;

    }

    public static String decrypt(byte[] input, byte[] key) throws Exception {
        SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, originalKey);
//        byte[] decrypted = cipher.doFinal(input);
        byte[] decordedValue = Base64.decode(input, Base64.DEFAULT);
        byte[] decValue = cipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        String decoded=new String(Base64.decode(decryptedValue,Base64.DEFAULT));
        return decoded;
//        return decrypted;
    }
}
