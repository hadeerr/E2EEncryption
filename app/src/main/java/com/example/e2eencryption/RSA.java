package com.example.e2eencryption;
import android.util.Base64;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

public class RSA {
    public static final String KEY_ALGORITHM = "RSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";



    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decode(key, Base64.DEFAULT);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.encodeToString(key, Base64.DEFAULT);
    }


    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return encryptBASE64(key.getEncoded());
    }

    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return encryptBASE64(key.getEncoded());
    }


    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();


        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }



    public static byte[] encrypt(Key publicKey, String s) throws Exception{
        byte[] byteData = s.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(byteData);


        return encryptedData;
    }


    public static byte[] decrypt(Key privatekey, byte[]  s) throws Exception{

        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privatekey);
       byte[] encryptedData = cipher.doFinal(s);


        return encryptedData;
    }





//
//    public  void main() throws Exception{
//        KeyPair keyPair = buildKeyPair();
//        PublicKey pubKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//        txt_public_key.setText("PUBLIC KEY  "+pubKey+"");
//        txt_private_key.setText("PRIVATE KEY  "+privateKey+"");
//
//        // sign the message
//        byte [] signed = encrypt(privateKey, "This is a secret message");
//        System.out.println(new String(signed));// <<signed message>>
//        txt_encrypted_message.setText("ENCRYPTED MESSAGE  " +new String(signed));
//
//
//        // verify the message
//        byte[] verified = decrypt(pubKey, signed);
//        System.out.println(new String(verified));
//        txt_decrypted_message.setText("DECRYPTED MESSAGE  "+new String(verified));
//        txt_original_message.setText("ORIGINAL MESSAGE   This is a secret message");
//    }
//
//
//    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
//        final int keySize = 2048;
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(keySize);
//        return keyPairGenerator.genKeyPair();
//    }
//
//
//    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//
//        return cipher.doFinal(message.getBytes());
//    }
//
//    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//
//        return cipher.doFinal(encrypted);
//    }



    //        try {
//            main();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

}
