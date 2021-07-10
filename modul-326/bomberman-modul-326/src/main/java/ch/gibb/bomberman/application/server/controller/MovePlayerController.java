package ch.gibb.bomberman.application.server.controller;

import ch.gibb.bomberman.application.server.BombermanServer;
import ch.gibb.bomberman.application.server.game.Game;
import ch.gibb.bomberman.protocol.Direction;

public class MovePlayerController extends Controller {

    private Direction direction;

    MovePlayerController(String playerName) {
        super(playerName);
    }

    @Override
    public void handle(BombermanServer serverRef) {
        System.out.println("Player " + playerName + " has moved!");
    }
}