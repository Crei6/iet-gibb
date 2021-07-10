package ch.gibb.bomberman.application.server.connection;

import ch.gibb.bomberman.protocol.client2server.ClientMessage;

public class MessageWrapper {
    private ClientMessage message;
    private String connectionId;

    public MessageWrapper(int messageID, ClientMessage message, String connectionId) {
        this.message = message;
        this.connectionId = connectionId;
    }

    public ClientMessage getMessage() {
        return message;
    }

    public String getConnectionID() {
        return connectionId;
    }
}
