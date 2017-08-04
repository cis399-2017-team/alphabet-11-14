import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.Scanner;

public class AlphabetChatClient implements Runnable {

    public static String server_host = "alphabet13.naookiesato.com";
    public static int server_port = 10000;
    
    protected DataInputStream i;
    protected DataOutputStream o;
    protected Thread listener;

    public AlphabetChatClient(InputStream i, OutputStream o) {
        this.i = new DataInputStream( new BufferedInputStream(i) );
        this.o = new DataOutputStream( new BufferedOutputStream(o) );
        listener = new Thread(this); //create a thread this way, or have ChatClient extent Thread
        listener.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String line = i.readUTF();
                System.out.printf(line + "\n");
            }
        } catch (IOException e) { 
            e.printStackTrace();
        } finally {
            listener = null;
            try { 
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        try {
            o.writeUTF(message);
            o.flush();
        } catch (IOException e) {
            e.printStackTrace();
            listener.stop();
        }
    }
    
    public static void main(String args[]) throws IOException {
        Socket s = new Socket();
        s.connect(new InetSocketAddress(server_host, server_port));
        System.out.println("new socket created, connected to " + s.getInetAddress().toString());

        AlphabetChatClient client = new AlphabetChatClient(s.getInputStream(), s.getOutputStream());

        System.out.println("new client created, connected at " + s.getLocalAddress().toString());

        Scanner sc = new Scanner(System.in);
        String m = "";

        while (true) {
            try {
                m = sc.nextLine();
            } catch (Exception e) {
                if (m.isEmpty()) {
                    System.out.println("Empty message");
                }
            }
            client.sendMessage(s.getLocalAddress() + ": " + m);
        }
    }
}
