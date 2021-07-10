package ch.gibb.bomberman.application.server.connection;

import ch.gibb.bomberman.protocol.parser.MessageParser;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnection {
    private ServerSocket serverSocket;
    private int port;
    public static int clients = 0;
    private static final MessageParser parser = new MessageParser();

    public void establish(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        System.out.println("JSONServer has been established on port " + port);

    }

    public void accept() throws IOException {
        while (true) {
            Runnable r = new MyThreadHandler(serverSocket.accept());
            Thread t = new Thread(r);
            t.start();
        }
    }

    private static class MyThreadHandler implements Runnable {
        private final Socket socket;

        MyThreadHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            clients++;
            System.out.println(clients + " JSONClient(s) connected on port: " + socket.getPort());

            try {
                JSONObject jsonObject = receiveJSON();
                sendJSON(jsonObject);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    closeSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

            System.out.println("Got from client on port " + socket.getPort() + " " + jsonObject.get("test").toString());
            return jsonObject;
        }

        public void sendJSON(JSONObject jsonObject) throws IOException {
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(out);
            o.writeObject(jsonObject.toString());
            out.flush();
            System.out.println("Sent to Client: " + " " + jsonObject.get("test").toString());
        }
    }


    public void start(int port) throws IOException {
        establish(port);
        accept();
    }
}
