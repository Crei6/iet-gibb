package ch.gibb.bomberman.network.dummy;

import ch.gibb.bomberman.protocol.parser.MessageParser;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class DummyClient {
    String host;
    int port;
    Socket socket;
    final String DEFAULT_HOST = "localhost";

    private static MessageParser parser = new MessageParser();

    public void connect(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        socket = new Socket(host, port);
        System.out.println("Client has been connected..");
    }

    public JSONObject receiveJSON() throws IOException {
        InputStream in = socket.getInputStream();
        ObjectInputStream i = new ObjectInputStream(in);
        String line;
        try {
            line = (String) i.readObject();
            JSONObject jsonObject = new JSONObject(line);

            System.out.println("Got from client on port " + socket.getPort() + " " + jsonObject.get("key").toString());

            return jsonObject;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendJSON(JSONObject jsonObject) throws IOException {
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(out);
        o.writeObject(jsonObject.toString());
        out.flush();
        System.out.println("Sent to server: " + " " + jsonObject.get("key").toString());
    }
}
