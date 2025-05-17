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

            System.out.println("Klienti është gati. Shkruaj mesazhe për serverin (ose 'exit' për të dalë).");


            while (true) {
                System.out.print("> ");
                String message = scanner.nextLine();

                if (message == null || message.trim().isEmpty()) {
                    continue; // mos e dërgo nëse është bosh
                }

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Lidhja po mbyllet...");
                    socket.close();
                    break;
                }

                String encrypted = RSAUtils.encrypt(message, serverPublicKey);
                writer.write(encrypted);
                writer.newLine();
                writer.flush();
            }

        } catch (Exception e) {
            System.err.println("Gabim gjatë dërgimit të mesazhit: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
