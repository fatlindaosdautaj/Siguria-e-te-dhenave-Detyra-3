package tests;

import rsa.RSAUtils;

import java.security.KeyPair;

public class RSATests {
    public static void main(String[] args) {
        try {
            System.out.println(" Testimi i RSAUtils.java ka filluar...\n");

            // 1. Gjenerimi i çiftit të çelësave
            KeyPair keyPair = RSAUtils.generateKeyPair();
            System.out.println(" Çelësat u gjeneruan me sukses.");

            // 2. Enkriptimi i një mesazhi
            String message = "Test mesazh i sigurt!";
            String encrypted = RSAUtils.encrypt(message, keyPair.getPublic());
            System.out.println(" Mesazhi u enkriptua: " + encrypted);

            // 3. Dekriptimi i mesazhit
            String decrypted = RSAUtils.decrypt(encrypted, keyPair.getPrivate());
            System.out.println(" Mesazhi i dekriptuar: " + decrypted);

            // 4. Verifikimi
            if (message.equals(decrypted)) {
                System.out.println("\n Testi përfundoi me sukses! Enkriptimi dhe dekriptimi funksionojnë saktë.");
            } else {
                System.out.println("\n Testi dështoi. Mesazhi i dekriptuar nuk përputhet.");
            }

        } catch (Exception e) {
            System.out.println(" Gabim gjatë testimit: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
