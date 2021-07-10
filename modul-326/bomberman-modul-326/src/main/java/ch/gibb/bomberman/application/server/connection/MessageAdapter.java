package ch.gibb.bomberman.application.server.connection;

import ch.gibb.bomberman.application.server.BombermanServer;
import ch.gibb.bomberman.application.server.controller.Controller;
import ch.gibb.bomberman.application.server.controller.ControllerDispatcher;
import ch.gibb.bomberman.application.server.controller.ControllerFactory;
import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.protocol.client2server.ClientMessage;
import lombok.SneakyThrows;

public class MessageAdapter implements Runnable {
    private final BombermanServer serverRef;

    public MessageAdapter(BombermanServer serverRef) {
        this.serverRef = serverRef;
        new Thread(this).start();
    }

    public void handleMessage(MessageQueue messageQueue, Message message, String connectionId) {
        new MessageEntry(messageQueue).handleMessage(message, connectionId);
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            for (int messageI = 0; messageI <= serverRef.getQueue().getLength(); messageI++) {
                MessageWrapper messageWrapper = serverRef.getQueue().remove();
                ClientMessage message = messageWrapper.getMessage();
                ControllerDispatcher dispatcher = new ControllerDispatcher(new ControllerFactory());
                Controller controller = dispatcher.getController(message);
                controller.handle(serverRef);

            }
            if (serverRef.getQueue().getLength() == 0) {
                Thread.sleep(100);
            }
        }
    }
}