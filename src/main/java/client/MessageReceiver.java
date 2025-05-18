package client;

import rsa.RSAUtils;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.security.PrivateKey;

public class MessageReceiver implements Runnable {
    private final Socket socket;
    private final PrivateKey clientPrivateKey;

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

                if (encryptedMessage == null) {
                    // Socket u mbyll nga pala tjetër
                    System.out.println("Lidhja me serverin u mbyll.");
                    break;
                }

                String decrypted = RSAUtils.decrypt(encryptedMessage, clientPrivateKey);
                System.out.println("\n[Mesazh i pranuar] ➜ " + decrypted);
                System.out.print("> ");
            }

        } catch (SocketException e) {
            System.out.println("Lidhja është mbyllur. Receiver ndaloi.");
        } catch (Exception e) {
            System.err.println("Gabim në marrjen e mesazhit: " + e.getMessage());
        }
    }
}