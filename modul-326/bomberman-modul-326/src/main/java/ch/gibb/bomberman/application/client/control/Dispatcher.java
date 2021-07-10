package ch.gibb.bomberman.application.client.control;

import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.network.client.ClientApplicationInterface;
import ch.gibb.bomberman.protocol.server2client.ErrorMessage;
import ch.gibb.bomberman.protocol.server2client.PlayerJoined;
import ch.gibb.bomberman.protocol.server2client.StartGame;

public class Dispatcher implements ClientApplicationInterface {
    @Override
    public void handleMessage(Message message) {
        if (message instanceof PlayerJoined)  {
            PlayerJoinedControl playerJoinedControl = ControlFactory.instance().createPlayerJoinedControl();
            playerJoinedControl.PlayerJoined((PlayerJoined)message);
        } else if (message instanceof ErrorMessage) {

        } else if (message instanceof StartGame) {

        }

    }
}
