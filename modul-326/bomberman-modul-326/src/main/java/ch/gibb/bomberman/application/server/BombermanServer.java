package ch.gibb.bomberman.application.server;

import ch.gibb.bomberman.application.server.connection.MessageAdapter;
import ch.gibb.bomberman.application.server.connection.MessageQueue;

import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.network.server.ServerApplicationInterface;
import ch.gibb.bomberman.network.server.ServerImpl;
import ch.gibb.bomberman.application.server.game.Game;
import ch.gibb.bomberman.protocol.client2server.JoinGame;

import java.io.IOException;

public class BombermanServer implements ServerApplicationInterface {

    private final Game gameInstance;
    private ServerImpl serverImpl;
    private final MessageQueue queue;
    private MessageAdapter adapter;

    private BombermanServer() {
        super();
        this.gameInstance = new Game(this);
        this.serverImpl = ServerImpl.getNetworkInstance(this);
        this.queue = new MessageQueue();
    }

    public static void main(String[] args) throws IOException {
        BombermanServer serverApplication = new BombermanServer();
        serverApplication.adapter = new MessageAdapter(serverApplication);
        serverApplication.serverImpl.start(567);
    }

    @Override
    public void handleMessage(Message message, String connectionId) {
        adapter.handleMessage(this.queue, message, connectionId);
    }

    public void sendMessage(Message message) {
        serverImpl.broadcast(message);
    }

    public Game getGameInstance() {
        return this.gameInstance;
    }

    public MessageQueue getQueue() {
        return this.queue;
    }
}
