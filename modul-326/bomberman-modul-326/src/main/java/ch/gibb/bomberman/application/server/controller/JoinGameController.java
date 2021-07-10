package ch.gibb.bomberman.application.server.controller;

import ch.gibb.bomberman.application.server.BombermanServer;
import ch.gibb.bomberman.application.server.game.Game;
import ch.gibb.bomberman.protocol.server2client.PlayerJoined;

public class JoinGameController extends Controller {

    JoinGameController(String playerName) {
        super(playerName);
    }

    @Override
    public void handle(BombermanServer serverRef) {
        if (serverRef.getGameInstance().getPlayerHandler().hasPlayer(playerName)) {
            System.err.println("Player " + playerName + " already exists!");

        } else if (serverRef.getGameInstance().getPlayerHandler().getPlayerCount() >= 4) {
            System.err.println("Player " + playerName + " cannot join because room is already full!");
        } else {
            serverRef.getGameInstance().getPlayerHandler().createPlayer(playerName);
            //serverRef.sendMessage(new PlayerJoined(playerName, 0, 0));
            System.out.println("Player " + playerName + " joined the game!");
        }
    }
}