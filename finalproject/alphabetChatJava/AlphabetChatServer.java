import java.net.*;
import java.io.*;
import java.util.*;

public class AlphabetChatServer extends Thread {

    public static String server_host = "10.0.6.22";
    public static int server_port = 10000;

    protected Socket s;
    protected static Vector<AlphabetChatServer> threads = new Vector<AlphabetChatServer>();
    protected DataInputStream i;
    protected DataOutputStream o;

    public AlphabetChatServer(Socket s) throws IOException {
        this.s = s;
        this.i = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        this.o = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            threads.addElement(this);
            while(true) {
                String m = i.readUTF();
                broadcast(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threads.removeElement(this);
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void broadcast( String message ) {
        synchronized (threads) {
            Enumeration<AlphabetChatServer> en = threads.elements();
            while (en.hasMoreElements()) {
                AlphabetChatServer t = en.nextElement();
                try {
                    synchronized (t.o) {
                        t.o.writeUTF(message);
                    }
                    t.o.flush();
                } catch (IOException e) {
                    t.stop();
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(server_host, server_port));
        System.out.println("server socket created, inet address: " + server.getInetAddress());
        while (true) {
            Socket client = server.accept();
            System.out.println("Accepted from " + client.getInetAddress());
            AlphabetChatServer c = new AlphabetChatServer(client);
            c.start();
        }
    }
}
