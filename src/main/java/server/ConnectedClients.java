package server;

import java.net.Socket;
import java.security.PublicKey;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectedClients {
    private static final ConcurrentHashMap<Socket, PublicKey> clients = new ConcurrentHashMap<>();

    public static void addClient(Socket socket, PublicKey key) {
        clients.put(socket, key);
    }

    public static ConcurrentHashMap<Socket, PublicKey> getClients() {
        return clients;
    }

    public static void removeClient(Socket socket) {
        clients.remove(socket);
    }
}
