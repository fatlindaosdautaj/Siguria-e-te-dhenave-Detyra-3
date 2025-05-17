package rsa;

import java.security.PublicKey;
import java.util.concurrent.ConcurrentHashMap;

public class KeyExchange {
    // Strukturë që ruan çelësat publikë për çdo klient (p.sh. sipas emrit)
    private static final ConcurrentHashMap<String, PublicKey> publicKeys = new ConcurrentHashMap<>();

    // Shto çelësin publik për një klient
    public static void registerPublicKey(String clientName, PublicKey publicKey) {
        publicKeys.put(clientName, publicKey);
    }

    // Merr çelësin publik të një klienti
    public static PublicKey getPublicKey(String clientName) {
        return publicKeys.get(clientName);
    }

    // Kontrollo nëse ekziston një çelës publik për një klient të caktuar
    public static boolean hasPublicKey(String clientName) {
        return publicKeys.containsKey(clientName);
    }

    // Fshi çelësin publik kur klienti largohet
    public static void removePublicKey(String clientName) {
        publicKeys.remove(clientName);
    }

    // Lista emrat e klientëve të regjistruar
    public static String listClients() {
        return String.join(", ", publicKeys.keySet());
    }
}
