import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;

public class ChatServer {
    public ChatServer(String ip, int port) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(ip, port));
        System.out.println("server socket created, inet address: " + server.getInetAddress());
        while (true) {
            Socket client = server.accept();
            System.out.println("Accepted from " + client.getInetAddress());
            ChatThread c = new ChatThread(client);
            c.start();
        }
    }

    public static void main(String args[]) throws IOException {
        if (args.length != 2)
            throw new RuntimeException("Improper arguments: <server> <port>");
        new ChatServer(args[0], Integer.parseInt(args[1]));
    }
}
