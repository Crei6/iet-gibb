package ch.gibb.bomberman.application.client.control;

import ch.gibb.bomberman.application.client.model.Game;
import ch.gibb.bomberman.application.client.view.BombermanPanel;
import ch.gibb.bomberman.network.client.ServerProxy;
import ch.gibb.bomberman.network.server.Server;
import ch.gibb.bomberman.protocol.client2server.JoinGame;

public class JoinGameControl extends Control {
    public JoinGameControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
        super(serverProxy, game, view);
    }

    public void JoinGame(String playerName){
        super.game.createMyPlayer(playerName);
        JoinGame message = new JoinGame(playerName);
        super.serverProxy.send(message);
        //Screencast 3 minute 6
    }
}
