import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;

public class AlphabetChatServer {

    public static String server_host = "alphabet13.naookiesato.com";
    public static int server_port = 10000;

    public AlphabetChatServer(String ip, int port) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(ip, port));
        System.out.println("server socket created, inet address: " + server.getInetAddress());
        while (true) {
            Socket client = server.accept();
            System.out.println("Accepted from " + client.getInetAddress());
            AlphabetChatThread c = new AlphabetChatThread(client);
            c.start();
        }
    }

    public static void main(String args[]) throws IOException {
        new AlphabetChatServer(server_host, server_port);
    }
}
