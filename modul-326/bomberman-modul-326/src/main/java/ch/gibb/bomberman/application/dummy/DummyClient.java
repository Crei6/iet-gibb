package ch.gibb.bomberman.application.dummy;

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
        String line = null;
        try {
            line = (String) i.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(line);

        System.out.println("Got from client on port " + socket.getLocalPort() + " " + jsonObject.get("key").toString());
        return jsonObject;
    }

    public void sendJSON(JSONObject jsonObject) throws IOException {
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(out);
        o.writeObject(jsonObject.toString());
        out.flush();
        System.out.println("Sent to server: " + " " + jsonObject.get("test").toString());
    }

    public static void main(String[] args) {
        try {
            DummyClient client = new DummyClient();
            client.connect("localhost", 6969);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("test", 5);
            client.sendJSON(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}