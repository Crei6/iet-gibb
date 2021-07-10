package ch.gibb.bomberman.application.client.control;

import ch.gibb.bomberman.application.client.model.Game;
import ch.gibb.bomberman.application.client.view.BombermanPanel;
import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.network.client.ServerProxy;
import ch.gibb.bomberman.protocol.server2client.PlayerJoined;

public class PlayerJoinedControl extends Control
{
    public PlayerJoinedControl(ServerProxy serverProxy, Game game, BombermanPanel view)
    {
        super(serverProxy, game, view);
    }

    public void PlayerJoined(PlayerJoined message)
    {
        game.playerJoined(message);
        view.displayMessage(message.getPlayerName() + " logged in");
    }
}
