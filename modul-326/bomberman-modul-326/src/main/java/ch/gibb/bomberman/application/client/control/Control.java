package ch.gibb.bomberman.application.client.control;

import ch.gibb.bomberman.application.client.model.Game;
import ch.gibb.bomberman.application.client.view.BombermanPanel;
import ch.gibb.bomberman.network.client.ServerProxy;

public class Control {
    ServerProxy serverProxy;
    Game game;
    BombermanPanel view;

    public Control(ServerProxy serverProxy, Game game, BombermanPanel view) {
        this.serverProxy = serverProxy;
        this.game = game;
        this.view = view;
    }
}
