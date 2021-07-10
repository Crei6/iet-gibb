package ch.gibb.bomberman.application.server.controller;

import ch.gibb.bomberman.application.server.BombermanServer;
import ch.gibb.bomberman.application.server.game.Game;

public class DropBombController extends Controller {

    private int positionX;
    private int positionY;

    DropBombController(String playerName) {
        super(playerName);
    }

    @Override
    public void handle(BombermanServer serverRef) {
        System.out.println("Player " + playerName + " has dropped a bomb!");
    }
}