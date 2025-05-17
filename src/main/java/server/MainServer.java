package server;

import rsa.RSAUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class MainServer {
    private static final int PORT = 12345;
    private static KeyPair serverKeyPair;
    private static final ConcurrentHashMap<Socket, PublicKey> connectedClients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[*] Serveri startoi në portin " + PORT);

            try {
                serverKeyPair = RSAUtils.generateKeyPair();
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Gabim gjatë gjenerimit të çelësave RSA.");
                e.printStackTrace();
                return;
            }

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[+] Klient i lidhur: " + clientSocket.getInetAddress());

                // Starto një thread të ri për çdo klient
                new Thread(new ClientHandler(clientSocket, serverKeyPair)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addClient(Socket socket, PublicKey key) {
        connectedClients.put(socket, key);
    }

    public static PublicKey getClientKey(Socket socket) {
        return connectedClients.get(socket);
    }
}
