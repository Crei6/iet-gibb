package ch.gibb.bomberman.network.dummy;

import ch.gibb.bomberman.protocol.client2server.JoinGame;
import ch.gibb.bomberman.protocol.parser.MessageParser;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DummyServer {
    private ServerSocket serverSocket;
    private int port;
    public static int clients = 0;
    private static MessageParser parser = new MessageParser();

    public void establish(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        System.out.println("JSONServer has been established on port " + port);

    }

    public void accept() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            Runnable r = new MyThreadHandler(socket);
            Thread t = new Thread(r);
            t.start();
        }
    }

    public static class MyThreadHandler implements Runnable {
        private Socket socket;

        public MyThreadHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            clients++;
            System.out.println(clients + " JSONClient(s) connected on port: " + socket.getPort());

            try {
                    while (true) {
                        JSONObject jsonObject = receiveJSON();
                        sendJSON(jsonObject);
                    }

            } catch (IOException e) {

            } finally {

            }
        }

        public void closeSocket() throws IOException {
            socket.close();
        }

        public JSONObject receiveJSON() throws IOException {
            InputStream in = socket.getInputStream();
            ObjectInputStream i = new ObjectInputStream(in);
            String line = null;
            try {
                line = (String) i.readObject();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = new JSONObject(line);

            System.out.println("Got from client on port " + socket.getPort() + " " + jsonObject.get("key").toString());
            return jsonObject;
        }

        public void sendJSON(JSONObject jsonObject) throws IOException {
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(out);
            o.writeObject(jsonObject.toString());
            out.flush();
            System.out.println("Sent to Client: " + " " + jsonObject.get("key").toString());
        }
    }

    public void start(int port) throws IOException{
        establish(port);
        accept();
    }

    public static void main(String[] args) {
        DummyServer server = new DummyServer();

        try {
            server.start(7777);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println(e);
        }
    }
}
