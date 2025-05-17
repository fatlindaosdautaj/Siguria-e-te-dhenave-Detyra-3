package client;

import rsa.RSAUtils;

import java.io.*;
import java.net.Socket;
import java.security.PrivateKey;

public class MessageReceiver implements Runnable {
    private Socket socket;
    private PrivateKey clientPrivateKey;

    public MessageReceiver(Socket socket, PrivateKey clientPrivateKey) {
        this.socket = socket;
        this.clientPrivateKey = clientPrivateKey;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String encryptedMessage = reader.readLine();
                if (encryptedMessage != null) {
                    String decrypted = RSAUtils.decrypt(encryptedMessage, clientPrivateKey);
                    System.out.println("\n[Mesazh i pranuar] ➜ " + decrypted);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
