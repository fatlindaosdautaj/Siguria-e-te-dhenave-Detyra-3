package rsa;

import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.Cipher;
import java.io.*;

public class RSAUtils {

    private static final int KEY_SIZE = 2048;

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(KEY_SIZE);
        return keyGen.generateKeyPair();
    }

    public static String encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(bytes), "UTF-8");
    }

    public static String publicKeyToString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public static PublicKey stringToPublicKey(String publicKeyStr) throws Exception {
        byte[] byteKey = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(X509publicKey);
    }

    public static void saveKeyPair(KeyPair keyPair, String directory, String alias) throws IOException {
        File pubFile = new File(directory, alias + "_public.key");
        File privFile = new File(directory, alias + "_private.key");

        try (FileOutputStream fos = new FileOutputStream(pubFile)) {
            fos.write(keyPair.getPublic().getEncoded());
        }

        try (FileOutputStream fos = new FileOutputStream(privFile)) {
            fos.write(keyPair.getPrivate().getEncoded());
        }
    }

    public static KeyPair loadKeyPair(String directory, String alias) throws Exception {
        File pubFile = new File(directory, alias + "_public.key");
        File privFile = new File(directory, alias + "_private.key");

        byte[] pubBytes = new byte[(int) pubFile.length()];
        byte[] privBytes = new byte[(int) privFile.length()];

        try (FileInputStream fis = new FileInputStream(pubFile)) {
            fis.read(pubBytes);
        }

        try (FileInputStream fis = new FileInputStream(privFile)) {
            fis.read(privBytes);
        }

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(pubBytes));
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privBytes));

        return new KeyPair(publicKey, privateKey);
    }
}