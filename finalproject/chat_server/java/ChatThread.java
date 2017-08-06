import java.net.*;
import java.io.*;
import java.util.*;

public class ChatThread extends Thread {

    protected Socket s;
    protected static Vector<ChatThread> threads = new Vector<ChatThread>();
    protected DataInputStream i;
    protected DataOutputStream o;

    public ChatThread(Socket s) throws IOException {
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
            Enumeration<ChatThread> en = threads.elements();
            while (en.hasMoreElements()) {
                ChatThread t = en.nextElement();
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
}
