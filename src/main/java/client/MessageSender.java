package client;

import rsa.RSAUtils;

import java.io.*;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

public class MessageSender implements Runnable {
    private Socket socket;
    private PublicKey serverPublicKey;

    public MessageSender(Socket socket, PublicKey serverPublicKey) {
        this.socket = socket;
        this.serverPublicKey = serverPublicKey;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                System.out.print("Shkruaj mesazhin për të dërguar: ");
                String message = scanner.nextLine();

                String encrypted = RSAUtils.encrypt(message, serverPublicKey);
                writer.write(encrypted);
                writer.newLine();
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
