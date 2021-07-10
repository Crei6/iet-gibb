package ch.gibb.bomberman.network.server;

import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.protocol.parser.MessageParser;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ServerImpl extends Server {

    private static ServerImpl networkInstance;

    private ServerSocket serverSocket;
    private int port;
    public static int clients = 0;
    private static MessageParser parser = new MessageParser();
    private List<MyThreadHandler> clientList = new Vector<>();

    /**
     * Konstruktor. Initialisiert das neue Server-Objekt mit der Referenz auf das Empfängerobjekt.
     *
     * @param serverApplication Das Empfängerobjekt des Bomberman-Servers für Nachrichten.
     */
    private ServerImpl(ServerApplicationInterface serverApplication) {
        super(serverApplication);
    }

    @Override
    public void send(Message message, String connectionId) {

        broadcast(message);
    }

    @Override
    public void broadcast(Message message) {

        for (MyThreadHandler client : clientList) {

            try {
                client.sendMessage(message);

            } catch (IOException e) {
                System.out.println("oops");
            }
        }
    }

    public void receive() {

        for (MyThreadHandler client : clientList) {

            try {
                client.receiveMessage();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("oops");
            }
        }
    }

    /**
     * Singleton access of ServerImplementation
     *
     * @param serverApplication Object of type <ServerApplicationInterface>
     * @return instance if already instantiated
     */
    public static ServerImpl getNetworkInstance(ServerApplicationInterface serverApplication) {

        if (networkInstance == null) {

            return new ServerImpl(serverApplication);
        }

        return networkInstance;
    }

    /**
     *
     * NEW STUFF TAKEN FROM DUMMY ENVIRONMENT
     *
     */

    public void establish(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        System.out.println("JSONServer has been established on port " + port);
    }

    public void start(int port) throws IOException{
        establish(port);
        accept();
    }

    public void accept() throws IOException {
        // FIXME: actually stop when game finishes
        while (true) {
            Socket socket = serverSocket.accept();
            MyThreadHandler threadHandler = new ServerImpl.MyThreadHandler(socket, serverApplication);
            clientList.add(threadHandler);
            Thread t = new Thread(threadHandler);
            t.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyThreadHandler implements Runnable {
        private Socket socket;
        private ServerApplicationInterface serverApplication;
        private ObjectOutputStream out;

        MyThreadHandler(Socket socket, ServerApplicationInterface serverApplication) {
            this.socket = socket;
            this.serverApplication = serverApplication;
            try {
                out = new ObjectOutputStream(socket.getOutputStream());

            } catch (Exception e) {
                System.out.println("We don't care");
            }
        }

        @SneakyThrows
        @Override
        public void run() {
            clients++;
            System.out.println(clients + " JSONClient(s) connected on port: " + socket.getPort());
            while (true) {
                receiveMessage();
                Thread.sleep(100);
            }
        }

        public void closeSocket() throws IOException {
            socket.close();
        }

        public void receiveMessage() throws IOException, ClassNotFoundException {

            InputStream in = socket.getInputStream();
            String line = null;
            try {
                line = parser.byteArrayToString(in.readAllBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = new JSONObject(line);

            System.out.println("Got from client on port " + socket.getPort());

            Message message = (Message) parser.jsonToJava(jsonObject);
            this.serverApplication.handleMessage(message, "nice try but there's no id");
        }

        public void sendJSON(JSONObject jsonObject) throws IOException {

            out.writeObject(jsonObject.toString());

            out.flush();
            System.out.println("Sent JSON to Client: " + jsonObject.get("key").toString());

        }

        public void sendMessage(Message msg) throws IOException {

            out.writeObject(msg);

            out.flush();
            System.out.println("Sent Message Type of " + msg.getClass().getSimpleName());
        }
    }
}


