package client;

import rsa.RSAUtils;

import java.io.*;
import java.net.Socket;
import java.security.*;
import java.util.Scanner;

public class MainClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            // Lidhja me serverin
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("[+] I lidhur me serverin...");

            // Gjenerimi i çelësave të klientit
            KeyPair clientKeyPair = RSAUtils.generateKeyPair();
            String publicKeyStr = RSAUtils.publicKeyToString(clientKeyPair.getPublic());

            // Dërgo çelësin publik te serveri
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(publicKeyStr);
            writer.newLine();
            writer.flush();

            // Lexo çelësin publik të serverit
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverPublicKeyStr = reader.readLine();
            PublicKey serverPublicKey = RSAUtils.stringToPublicKey(serverPublicKeyStr);

            // Starto thread-et për dërgim dhe pritje të mesazheve
            new Thread(new MessageSender(socket, serverPublicKey)).start();
            new Thread(new MessageReceiver(socket, clientKeyPair.getPrivate())).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
