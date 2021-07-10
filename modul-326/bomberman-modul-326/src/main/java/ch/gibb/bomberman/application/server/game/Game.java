package ch.gibb.bomberman.application.server.game;

import ch.gibb.bomberman.application.server.BombermanServer;
import ch.gibb.bomberman.application.server.game.labyrinth.Labyrinth;
import ch.gibb.bomberman.application.server.game.player.PlayerHandler;
import ch.gibb.bomberman.protocol.server2client.StartGame;
import lombok.SneakyThrows;

public class Game implements Runnable {
    private boolean started;
    private final BombermanServer serverRef;
    private final PlayerHandler playerHandler;
    private final Labyrinth labyrinth;

    public Game(BombermanServer serverRef) {
        super();
        this.serverRef = serverRef;
        this.playerHandler = new PlayerHandler();
        this.labyrinth = new Labyrinth();
        new Thread(this).start();
    }

    public PlayerHandler getPlayerHandler() {
        return this.playerHandler;
    }

    public void start() {
        System.out.println("Game has started");
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            if (playerHandler.getPlayerCount() >= 4) {
                if (!started) {
                    start();
                    serverRef.sendMessage(new StartGame(labyrinth.toCharMap()));
                }
                started = true;
            }
            Thread.sleep(100);
        }
    }
}