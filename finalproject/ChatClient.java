import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.Scanner;

public class ChatClient implements Runnable {
    
    protected DataInputStream i;
    protected DataOutputStream o;
    protected Thread listener;

    public ChatClient(InputStream i, OutputStream o) {
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
        if (args.length != 2)
            throw new RuntimeException("Improper arguments");
        Socket s = new Socket( args[0], Integer.parseInt(args[1]) );
        System.out.println("new socket created, connected to " + s.getInetAddress().toString());
        ChatClient client = new ChatClient(s.getInputStream(), s.getOutputStream());

        System.out.println("new client created, connected to " + s.getInetAddress().toString());

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
            client.sendMessage(m);
        }
    }
}
