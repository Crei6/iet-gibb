package ch.gibb.bomberman.network.client;

import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.protocol.parser.MessageParser;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class ServerProxyImpl extends ServerProxy {

    private MessageParser parser;

    public static String host;
    public static int port;
    private static Socket socket;
    public String DEFAULT_HOST = "localhost";

    private static ServerProxyImpl proxyInstance;
    /**
     * Konstruktor. Initialisiert das neue ServerProxy-Objekt mit der Referenz auf das Empfängerobjekt.
     *
     * @param clientApplication Das Empfängerobjekt des Bomberman-Clients für Nachrichten.
     */
    public ServerProxyImpl(ClientApplicationInterface clientApplication, String host, int port) {
        super(clientApplication);
        parser = new MessageParser();

        if (host != null) {
            this.host = host;
        } else {
            this.host = DEFAULT_HOST;
        }
        this.port = port;
    }

    /**
     * Sendet ein Nachrichtenobjekt an den Server. Diese Methode muss innerhalb der Netzwerkschicht
     * implementiert werden.
     *
     * @param message Das Nachrichtenobjekt, welches an den Server gesendet werden soll.
     */
    @Override
    public void send(Message message) throws IOException {

        
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(out);
        JSONObject messageOut = parser.createJsonObj(message);
        o.writeObject(messageOut.toString());
        out.flush();
        System.out.println("Sent to server: " + " " + messageOut.get("key").toString());


    }

    public void startListening() {

        while (true) {
            receive();
        }
    }

    public Message receive() {

        try {
            InputStream in = socket.getInputStream();
            ObjectInputStream i = new ObjectInputStream(in);
            String line;

            line = (String) i.readObject();
            JSONObject jsonObject = new JSONObject(line);

            System.out.println("Got from client on port " + socket.getPort() + " " + jsonObject.get("key").toString());

            Message message = (Message)parser.jsonToJava(jsonObject);
            this.clientApplication.handleMessage(message);
            return message;

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ServerProxyImpl getProxyInstance(ClientApplicationInterface clientApplication) throws IOException {

        try {
            connect();
        } catch (Exception e) {
            System.out.println("Could not connect to server");
        }

        if (proxyInstance == null) {

            return new ServerProxyImpl(clientApplication,this.host,this.port);
        }

        return proxyInstance;
    }

    public void connect() throws IOException {
        socket = new Socket(host, this.port);
        System.out.println("Client has been connected..");
    }
}