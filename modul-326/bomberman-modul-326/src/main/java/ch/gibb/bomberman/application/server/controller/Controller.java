package ch.gibb.bomberman.application.server.controller;

import ch.gibb.bomberman.application.server.BombermanServer;
import ch.gibb.bomberman.application.server.game.Game;

public abstract class Controller {

    protected String playerName;

    Controller(String playerName) {
        this.playerName = playerName;
    }

    public abstract void handle(BombermanServer serverRef);
}