package server;

import rsa.RSAUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final BufferedReader input;
    private final BufferedWriter output;
    private final KeyPair serverKeyPair;

    public ClientHandler(Socket socket, KeyPair serverKeyPair) throws IOException {
        this.socket = socket;
        this.serverKeyPair = serverKeyPair;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            // Lexo çelësin publik të klientit (si string)
            String clientKeyStr = input.readLine();
            PublicKey clientKey = RSAUtils.stringToPublicKey(clientKeyStr);
            MainServer.addClient(socket, clientKey);

            // Dërgo çelësin publik të serverit (si string)
            String serverKeyStr = RSAUtils.publicKeyToString(serverKeyPair.getPublic());
            output.write(serverKeyStr);
            output.newLine();
            output.flush();

            System.out.println("[*] U shkëmbyen çelësat me klientin: " + socket.getInetAddress());

            // Prit mesazhe të enkriptuara
            String encryptedMessage;
            while ((encryptedMessage = input.readLine()) != null) {
                String decryptedMessage = decryptMessage(encryptedMessage);
                System.out.println("[Mesazh nga klienti] " + decryptedMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String decryptMessage(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, serverKeyPair.getPrivate());
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decryptedBytes);
    }
    
}
