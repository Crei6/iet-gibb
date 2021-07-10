package ch.gibb.bomberman.network.dummy;

import ch.gibb.bomberman.network.client.ServerProxy;
import ch.gibb.bomberman.network.client.ServerProxyImpl;
import ch.gibb.bomberman.network.server.ServerImpl;
import ch.gibb.bomberman.protocol.client2server.JoinGame;
import ch.gibb.bomberman.protocol.parser.MessageParser;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;

/**
 * this is a sample class to demonstrate the connection of a client
 * before executing start the server first
 * (main entry in the testserver class)
 */
public class DummyClientConnection {

    public static void main(String[] args) {

        DummyClientConnection d = new DummyClientConnection();
        d.startTest();
    }

    public void startTest() {
        /*
        DummyClient client = new DummyClient();

        try {
            MessageParser parser = new MessageParser();

            client.connect("localhost", 7777);

            JSONObject jsonObject1 = parser.createJsonObj(new JoinGame("jonny"));
            JSONObject jsonObject2 = parser.createJsonObj(new JoinGame("peter"));
            JSONObject jsonObject3 = parser.createJsonObj(new JoinGame("jeff"));

            client.sendJSON(jsonObject1);
            //Object test1 = client.getObjectFromJsonObject(client.receiveJSON());
            Thread.sleep(2000);

            client.sendJSON(jsonObject2);
            Object test2 = client.getObjectFromJsonObject(client.receiveJSON());
            Thread.sleep(2000);

            client.sendJSON(jsonObject);
        }

        catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                client.socket.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        */
    }
}
