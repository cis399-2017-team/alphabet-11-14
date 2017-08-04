import java.net.*;
import java.io.*;
import java.util.*;

public class AlphabetChatThread extends Thread {

    protected Socket s;
    protected static Vector<AlphabetChatThread> threads = new Vector<AlphabetChatThread>();
    protected DataInputStream i;
    protected DataOutputStream o;

    public AlphabetChatThread(Socket s) throws IOException {
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
            Enumeration<AlphabetChatThread> en = threads.elements();
            while (en.hasMoreElements()) {
                AlphabetChatThread t = en.nextElement();
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
