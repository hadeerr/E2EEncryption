package com.example.e2eencryption;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

import javax.crypto.SecretKey;

import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txt_private_key)
     TextView txt_private_key;
    @BindView(R.id.txt_public_key)
     TextView txt_public_key;
    @BindView(R.id.txt_encrypted_message)
     TextView txt_encrypted_message;
    @BindView(R.id.txt_decrypted_message)
     TextView txt_decrypted_message;
    @BindView(R.id.txt_original_message)
     TextView txt_original_message;
    @BindView(R.id.txt_encrypted_key)
     TextView txt_encrypted_key;
    @BindView(R.id.txt_decrypted_key)
     TextView txt_decrypted_key;
    @BindView(R.id.txt_encrypted_key2)
     TextView txt_encrypted_key2;
    @BindView(R.id.txt_decrypted_key2)
     TextView txt_decrypted_key2;
    @BindView(R.id.txt_secret_key)
     TextView txt_secret_key;
    @BindView(R.id.txt_secret_key2)
     TextView txt_secret_key2;
    @BindView(R.id.txt_secret_public_key)
     TextView txt_secret_public_key;
    @BindView(R.id.txt_secret_private_key)
     TextView txt_secret_private_key;
    @BindView(R.id.txt_secret_enc_message_key)
     TextView txt_secret_enc_message;
    @BindView(R.id.txt_secret_dec_message_key)
     TextView txt_secret_dec_message;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            Map<String, Object> map = RSA.initKey();
            PublicKey key = (PublicKey) map.get("RSAPublicKey");
            PrivateKey key2 = (PrivateKey) map.get("RSAPrivateKey");
            txt_public_key.setText("PUBLIC KEY  \n"+key+"");
            txt_private_key.setText("PRIVATE KEY  \n"+key2+"");
            txt_encrypted_message.setText("ENCRYPTED MESSAGE  \n" +new String(RSA.encrypt(key, "this is a secret message ")));
            txt_decrypted_message.setText("DECRYPTED MESSAGE  \n"+new String(RSA.decrypt(key2 , RSA.encrypt(key, "this is a secret message "))));
            txt_original_message.setText("ORIGINAL MESSAGE   \nthis is a secret message ");
            String encryptedpublickey = RSA.getPublicKey(map);
            String encryptedprivatekey = RSA.getPrivateKey(map);
            txt_encrypted_key.setText("BASE64 ENC PUBLIC KEY    \n"+encryptedpublickey);
            txt_encrypted_key2.setText("BASE64 ENC PRIVATE KEY    \n"+encryptedprivatekey);

            //////////////////////////////////////////////////////////////////////////////////////
            KeyPair alice = DiffHelman.generateKeyPair();
            KeyPair bob = DiffHelman.generateKeyPair();
            SecretKey encreptKey = DiffHelman.combine(alice.getPrivate(), bob.getPublic());
            SecretKey decreptedKey = DiffHelman.combine(bob.getPrivate(), alice.getPublic());

            txt_secret_key.setText("ENCRYPTED SECRET KEY \n"+new String(encreptKey.getEncoded()));
            txt_secret_key2.setText("DECRYPTED SECRET KEY \n"+new String(decreptedKey.getEncoded()));
            System.out.println(new String(encreptKey.getEncoded()));
            System.out.println(new String(decreptedKey.getEncoded()));
            String encreptedMessage = DiffHelman.encrypt("this is a secret message ".getBytes(), encreptKey);
            System.out.println(encreptedMessage);
            txt_secret_enc_message.setText("ENCRYPTED MESSAGE SECRET \n"+encreptedMessage);
            String decreptedMessage = DiffHelman.decrypt(encreptedMessage.getBytes(), decreptedKey);
            System.out.println(decreptedMessage);
            txt_secret_dec_message.setText("DECRYPTED MESSAGE SECRET \n"+decreptedMessage);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
