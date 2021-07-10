package ch.gibb.bomberman.application.server.connection;

import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.network.server.ServerApplicationInterface;
import ch.gibb.bomberman.protocol.client2server.ClientMessage;


public class MessageEntry implements ServerApplicationInterface {
    private MessageQueue messageQueue;

    public MessageEntry(MessageQueue queue) {
        messageQueue = queue;
    }

    @Override
    public void handleMessage(Message message, String connectionId) {
        try {
            MessageWrapper wrapper = new MessageWrapper(messageQueue.getLength(), (ClientMessage) message, connectionId);
            messageQueue.append(wrapper);
        } catch (ClassCastException cce) {
            System.err.println("Message type " + message.getClass().getSimpleName() + " not valid as input!");
        }

    }
}
